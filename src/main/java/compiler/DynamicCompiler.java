package compiler;

import javax.tools.*;
import java.io.File;
import java.util.Arrays;

public class DynamicCompiler {

    public static void main(String[] args) throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> ds = new
                DiagnosticCollector<>();
        try (StandardJavaFileManager mgr =
                     compiler.getStandardFileManager(ds, null, null)) {
            File file =
                    new File("/Users/lvting/Documents/github/playground/src/main/java/compiler/MyClass.java");
            Iterable<? extends JavaFileObject> sources =
                    mgr.getJavaFileObjectsFromFiles(Arrays.
                            asList(file));
            JavaCompiler.CompilationTask task =
                    compiler.getTask(null, mgr, ds, null,
                            null, sources);
            task.call();
        }
        for (Diagnostic<? extends JavaFileObject>
                d : ds.getDiagnostics()) {
            System.out.format("Line: %d, %s in %s",
                    d.getLineNumber(), d.getMessage(null),
                    d.getSource().getName());
        }
    }
}
