package validation.js;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavascriptUnitTestPairTest {
	private String HTML_EXT = "*.html";
	private String JS_BASE_MAIN = "./src/main/webapp/resources/js";
	private String JS_BASE_TEST = "./src/test/javascript";
	private String JS_EXT = "*.js";
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void testValidation() throws IOException {
		List<String> listMain = listJavaScript(JS_BASE_MAIN, JS_EXT);
		List<String> listTestHtml = listJavaScript(JS_BASE_TEST, HTML_EXT);
		List<String> listTestJs = listJavaScript(JS_BASE_TEST, JS_EXT);
		List<String> listMissing = new ArrayList<String>();
		for (final String item : listMain) {
			final String base = item.replaceAll("^" + JS_BASE_MAIN,
					JS_BASE_TEST);
			final String html = base.replaceAll(".js" + "$", "-test.html");
			if (!listTestHtml.contains(html)) {
				listMissing.add(html);
			}
			String js = base.replaceAll(".js" + "$", "-test.js");
			if (!listTestJs.contains(js)) {
				listMissing.add(html);
			}
		}
		assertEquals("These files are missing - " + listMissing.toArray(), 0,
				listMissing.size());
	}

	private List<String> listJavaScript(String base, String ext)
			throws IOException {
		final List<String> list = new ArrayList<String>();
		FileSystem fs = FileSystems.getDefault();
		PathMatcher matcher = fs.getPathMatcher("glob:" + ext);
		FileVisitor<Path> matcherVisitor = new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file,
					BasicFileAttributes attribs) {
				Path name = file.getFileName();
				if (matcher.matches(name)) {
					list.add(file.toString());
				}
				return FileVisitResult.CONTINUE;
			}
		};
		Files.walkFileTree(Paths.get(base), matcherVisitor);
		return list;
	}
}
