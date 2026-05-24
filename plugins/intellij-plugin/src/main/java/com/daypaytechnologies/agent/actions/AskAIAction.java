package com.daypaytechnologies.agent.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

public class AskAIAction extends AnAction {

    @Override
    public void actionPerformed(com.intellij.openapi.actionSystem.AnActionEvent e) {
        Project project = e.getProject();
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        if (editor == null) {
            return;
        }
        String selectedText = editor.getSelectionModel().getSelectedText();
        if (selectedText == null || selectedText.isEmpty()) {
            selectedText = editor.getDocument().getText();
        }
        String response = "AI Response for: " + selectedText;
        Messages.showInfoMessage(project, response, "AI Assistant");
    }
}
