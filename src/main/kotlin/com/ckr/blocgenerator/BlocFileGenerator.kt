package com.ckr.blocgenerator

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VirtualFile
import java.io.IOException

class BlocFileGenerator(
    private val project: Project,
    private val parentDir: VirtualFile,
    private val featureName: String
) {
    private val lowerCaseName = featureName.lowercase()
    private val pascalCaseName = toPascalCase(featureName)

    fun generate() {
        createDirectoryStructure()
        generateAllFiles()
    }

    private fun createDirectoryStructure() {
        val featureDir = VfsUtil.createDirectoryIfMissing(parentDir, lowerCaseName)
        VfsUtil.createDirectoryIfMissing(featureDir, "bloc")
        VfsUtil.createDirectoryIfMissing(featureDir, "models")
    }

    private fun generateAllFiles() {
        val featureDir = parentDir.findChild(lowerCaseName)!!
        val blocDir = featureDir.findChild("bloc")!!
        val modelsDir = featureDir.findChild("models")!!

        createFile(blocDir, "${lowerCaseName}_bloc.dart", generateBlocContent())
        createFile(blocDir, "${lowerCaseName}_event.dart", generateEventContent())
        createFile(blocDir, "${lowerCaseName}_state.dart", generateStateContent())
        createFile(modelsDir, "${lowerCaseName}_model.dart", generateModelContent())
        createFile(featureDir, "${lowerCaseName}_screen.dart", generateScreenContent())
    }

    private fun createFile(directory: VirtualFile, fileName: String, content: String) {
        try {
            val file = directory.createChildData(this, fileName)
            VfsUtil.saveText(file, content)
        } catch (e: IOException) {
            throw RuntimeException("Failed to create file: $fileName", e)
        }
    }

    private fun generateBlocContent(): String {
        return """
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:equatable/equatable.dart';
import '../models/${lowerCaseName}_model.dart';

part '${lowerCaseName}_event.dart';
part '${lowerCaseName}_state.dart';

class ${pascalCaseName}Bloc extends Bloc<${pascalCaseName}Event, ${pascalCaseName}State> {
  ${pascalCaseName}Bloc() : super(${pascalCaseName}Initial()) {
    on<Load${pascalCaseName}Event>(_onLoad${pascalCaseName});
    on<Update${pascalCaseName}Event>(_onUpdate${pascalCaseName});
  }

  Future<void> _onLoad${pascalCaseName}(
    Load${pascalCaseName}Event event,
    Emitter<${pascalCaseName}State> emit,
  ) async {
    emit(${pascalCaseName}Loading());
    
    try {
      // TODO: Implement your business logic here
      final ${lowerCaseName}Model = ${pascalCaseName}Model(
        id: '1',
        title: 'Sample ${pascalCaseName}',
        description: 'This is a sample description',
        createdAt: DateTime.now(),
      );
      
      emit(${pascalCaseName}Loaded(${lowerCaseName}Model));
    } catch (error) {
      emit(${pascalCaseName}Error(error.toString()));
    }
  }

  Future<void> _onUpdate${pascalCaseName}(
    Update${pascalCaseName}Event event,
    Emitter<${pascalCaseName}State> emit,
  ) async {
    emit(${pascalCaseName}Loading());
    
    try {
      emit(${pascalCaseName}Loaded(event.${lowerCaseName}Model));
    } catch (error) {
      emit(${pascalCaseName}Error(error.toString()));
    }
  }
}
""".trimIndent()
    }

    private fun generateEventContent(): String {
        return """
part of '${lowerCaseName}_bloc.dart';

abstract class ${pascalCaseName}Event extends Equatable {
  const ${pascalCaseName}Event();

  @override
  List<Object?> get props => [];
}

class Load${pascalCaseName}Event extends ${pascalCaseName}Event {
  const Load${pascalCaseName}Event();
}

class Update${pascalCaseName}Event extends ${pascalCaseName}Event {
  final ${pascalCaseName}Model ${lowerCaseName}Model;
  
  const Update${pascalCaseName}Event(this.${lowerCaseName}Model);

  @override
  List<Object?> get props => [${lowerCaseName}Model];
}
""".trimIndent()
    }

    private fun generateStateContent(): String {
        return """
part of '${lowerCaseName}_bloc.dart';

abstract class ${pascalCaseName}State extends Equatable {
  const ${pascalCaseName}State();

  @override
  List<Object?> get props => [];
}

class ${pascalCaseName}Initial extends ${pascalCaseName}State {}

class ${pascalCaseName}Loading extends ${pascalCaseName}State {}

class ${pascalCaseName}Loaded extends ${pascalCaseName}State {
  final ${pascalCaseName}Model ${lowerCaseName}Model;
  
  const ${pascalCaseName}Loaded(this.${lowerCaseName}Model);

  @override
  List<Object?> get props => [${lowerCaseName}Model];
}

class ${pascalCaseName}Error extends ${pascalCaseName}State {
  final String message;
  
  const ${pascalCaseName}Error(this.message);

  @override
  List<Object?> get props => [message];
}
""".trimIndent()
    }

    private fun generateModelContent(): String {
        return """
import 'package:equatable/equatable.dart';

class ${pascalCaseName}Model extends Equatable {
  final String id;
  final String title;
  final String description;
  final DateTime? createdAt;
  final DateTime? updatedAt;

  const ${pascalCaseName}Model({
    required this.id,
    required this.title,
    required this.description,
    this.createdAt,
    this.updatedAt,
  });

  factory ${pascalCaseName}Model.fromJson(Map<String, dynamic> json) {
    return ${pascalCaseName}Model(
      id: json['id'] ?? '',
      title: json['title'] ?? '',
      description: json['description'] ?? '',
      createdAt: json['created_at'] != null 
          ? DateTime.parse(json['created_at']) 
          : null,
      updatedAt: json['updated_at'] != null 
          ? DateTime.parse(json['updated_at']) 
          : null,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'title': title,
      'description': description,
      'created_at': createdAt?.toIso8601String(),
      'updated_at': updatedAt?.toIso8601String(),
    };
  }

  ${pascalCaseName}Model copyWith({
    String? id,
    String? title,
    String? description,
    DateTime? createdAt,
    DateTime? updatedAt,
  }) {
    return ${pascalCaseName}Model(
      id: id ?? this.id,
      title: title ?? this.title,
      description: description ?? this.description,
      createdAt: createdAt ?? this.createdAt,
      updatedAt: updatedAt ?? this.updatedAt,
    );
  }

  @override
  List<Object?> get props => [id, title, description, createdAt, updatedAt];
}
""".trimIndent()
    }

    private fun generateScreenContent(): String {
        return """
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'bloc/${lowerCaseName}_bloc.dart';

class ${pascalCaseName}Screen extends StatelessWidget {
  const ${pascalCaseName}Screen({super.key});

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (context) => ${pascalCaseName}Bloc()..add(const Load${pascalCaseName}Event()),
      child: const ${pascalCaseName}View(),
    );
  }
}

class ${pascalCaseName}View extends StatelessWidget {
  const ${pascalCaseName}View({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('${pascalCaseName}'),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      ),
      body: BlocBuilder<${pascalCaseName}Bloc, ${pascalCaseName}State>(
        builder: (context, state) {
          if (state is ${pascalCaseName}Initial) {
            return const Center(child: Text('Welcome to ${pascalCaseName}!'));
          }
          
          if (state is ${pascalCaseName}Loading) {
            return const Center(child: CircularProgressIndicator());
          }
          
          if (state is ${pascalCaseName}Loaded) {
            return Padding(
              padding: const EdgeInsets.all(16.0),
              child: Card(
                child: Padding(
                  padding: const EdgeInsets.all(16.0),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      Text(
                        state.${lowerCaseName}Model.title,
                        style: Theme.of(context).textTheme.headlineSmall,
                      ),
                      const SizedBox(height: 8),
                      Text(state.${lowerCaseName}Model.description),
                      const SizedBox(height: 8),
                      Text('ID: ${'$'}{state.${lowerCaseName}Model.id}'),
                    ],
                  ),
                ),
              ),
            );
          }
          
          if (state is ${pascalCaseName}Error) {
            return Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  const Icon(Icons.error, size: 64, color: Colors.red),
                  Text('Error: ${'$'}{state.message}'),
                  ElevatedButton(
                    onPressed: () => context.read<${pascalCaseName}Bloc>().add(const Load${pascalCaseName}Event()),
                    child: const Text('Retry'),
                  ),
                ],
              ),
            );
          }
          
          return const Center(child: Text('Unknown state'));
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () => context.read<${pascalCaseName}Bloc>().add(const Load${pascalCaseName}Event()),
        child: const Icon(Icons.refresh),
      ),
    );
  }
}
""".trimIndent()
    }

    private fun toPascalCase(input: String): String {
        return input.split("_", " ")
            .joinToString("") { word ->
                word.lowercase().replaceFirstChar { it.uppercase() }
            }
    }
}