package com.daypaytechnologies.agent.utils;

import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class MarkdownUtil {

    private static final Parser parser =
            Parser.builder().build();

    private static final HtmlRenderer renderer =
            HtmlRenderer.builder().build();

    public static String toHtml(String markdown) {

        return renderer.render(
                parser.parse(markdown)
        );
    }
}
