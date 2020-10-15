

package com.cts.bootup.run.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ShellCommand {

	@ShellMethod(value = "Add two integers together.", group = "Mathematical Commands")
	public int add(int a, int b) {
		return a + b;
	}
	
	@ShellMethod("Test completion of special values.")
	public String wGet(@ShellOption URL url) {
		return getContentsOfUrlAsString(url);
	}
	
	private String getContentsOfUrlAsString(URL url) {
		StringBuilder sb = new StringBuilder();
		try {
			try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					sb.append(inputLine);
				}
			}
		} catch (IOException ex) {
			sb.append("ERROR");
		}
		return sb.toString();
	}
	
}
