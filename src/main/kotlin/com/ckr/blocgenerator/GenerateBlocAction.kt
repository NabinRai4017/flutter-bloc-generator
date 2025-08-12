package com.ckr.blocgenerator

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile

class GenerateBlocAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val selectedDirectory = getSelectedDirectory(e) ?: return

        val featureName = Messages.showInputDialog(
            project,
            "Enter feature name (e.g., home, user_profile):",
            "Generate BLoC Pattern",
            Messages.getQuestionIcon(),
            "",
            null
        ) ?: return

        if (featureName.isBlank()) {
            Messages.showErrorDialog(project, "Feature name cannot be empty!", "Error")
            return
        }

        generateBlocFiles(project, selectedDirectory, featureName.trim())
    }

    private fun getSelectedDirectory(e: AnActionEvent): VirtualFile? {
        val project = e.project ?: return null
        val selectedFile = e.getData(CommonDataKeys.VIRTUAL_FILE)

        return if (selectedFile?.isDirectory == true) {
            selectedFile
        } else {
            selectedFile?.parent ?: project.baseDir
        }
    }

    private fun generateBlocFiles(project: Project, parentDir: VirtualFile, featureName: String) {
        WriteCommandAction.runWriteCommandAction(project) {
            try {
                val generator = BlocFileGenerator(project, parentDir, featureName)
                generator.generate()

                Messages.showInfoMessage(
                    project,
                    "BLoC pattern files generated successfully for '$featureName'!\n\nFiles created in: ${featureName.lowercase()}/",
                    "Success"
                )
            } catch (e: Exception) {
                Messages.showErrorDialog(
                    project,
                    "Failed to generate files: ${e.message}",
                    "Error"
                )
            }
        }
    }

    override fun update(e: AnActionEvent) {
        // Make the action always visible when there's a project
        val project = e.project
        e.presentation.isEnabledAndVisible = project != null

        // Optional: Add debug logging to see when this is called
        if (project != null) {
            val selectedFile = e.getData(CommonDataKeys.VIRTUAL_FILE)
            // This helps debug: you can check IDE logs to see if this is being called
            println("BLoC Generator: Action visible, project=${project.name}, selectedFile=${selectedFile?.name}")
        }
    }
}