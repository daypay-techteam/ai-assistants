package com.daypaytechnologies.agent.panels;

import com.daypaytechnologies.agent.services.ApiService;
import com.daypaytechnologies.agent.utils.MarkdownUtil;
import com.intellij.openapi.project.Project;

import javax.swing.*;

import java.awt.*;

public class ChatPanel extends JPanel {

    private final JTextPane chatArea;

    private final JTextField inputField;

    private final JButton sendButton;

    public ChatPanel(Project project) {
        setLayout(new BorderLayout());
        chatArea = new JTextPane();
        chatArea.setContentType("text/html");
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        inputField = new JTextField();
        sendButton = new JButton("Send");
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());
    }

    private void sendMessage () {
        String prompt = inputField.getText();
        if (prompt.isEmpty()) {
            return;
        }
        appendMessage("<b>You:</b> " + prompt + "<br/><br/>");
        inputField.setText("");
        new Thread(() -> {
            try {
                String response =
                        ApiService.ask(prompt);
                SwingUtilities.invokeLater(() -> {
                    appendMessage("<b>AI:</b><br/>" + MarkdownUtil.toHtml(response) + "<br/><br/>"
                    );
                });
            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> {
                    appendMessage("<span style='color:red'>" + ex.getMessage() + "</span><br/>"
                    );
                });
            }
        }).start();
    }

    private void appendMessage (String html){
        String current = chatArea.getText();
        chatArea.setText(current + html);
    }
}
