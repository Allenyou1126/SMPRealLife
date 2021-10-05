package wang.allenyou.smpreallife;

import java.util.Map;
import java.util.function.BiConsumer;

public class MessageRender {
	public static String renderMessage(String template, Map<String, String> placeHolders) {
		final String[] str = {template};
		placeHolders.forEach((placeholder, content) -> str[0] = str[0].replace("${" + placeholder + "}", content));
		return str[0];
	}
}
