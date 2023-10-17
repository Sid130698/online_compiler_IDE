package com.siddharth.OnlineWebIDE;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ide")
public class IdeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/idePage.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "compileCode":
                    String code = request.getParameter("code");
                    String language = request.getParameter("language");
                    String className = request.getParameter("className");
                    className = (className != null) ? className : "";
                    String result = compileCode(code, language,className);
                    response.setContentType("text/plain");
                    PrintWriter out = response.getWriter();
                    out.write(result);
                    break;

                default:
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    break;
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private String compileCode(String code, String language,String className) {
        try {
            switch (language.toLowerCase()) {
                case "java":
                    return compileJavaCode(code,className);
                case "python":
                    return compilePythonCode(code);
                default:
                    return "Unsupported language: " + language;
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private String compileJavaCode(String code, String className) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            return "Java compiler not available";
        }

        File sourceFile = null;
        try (StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null)) {
            sourceFile = new File(className + ".java");
            try (PrintWriter writer = new PrintWriter(sourceFile)) {
                writer.write(code);
            }

            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null,
                    fileManager.getJavaFileObjectsFromFiles(Collections.singletonList(sourceFile)));
            if (task.call()) {
                Process process = Runtime.getRuntime().exec("java " + className);
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
                return output.toString();
            } else {
                return "Compilation Error";
            }
        } catch (IOException e) {
            return "IOException: " + e.getMessage();
        } finally {
            if (sourceFile != null) {
                sourceFile.delete();
            }
        }
    }



    private String compilePythonCode(String code) {
        try {
            // Create a temporary Python file
            File tempFile = File.createTempFile("temp-python-", ".py");
            try (PrintWriter writer = new PrintWriter(tempFile)) {
                writer.write(code);
            }

            // Execute the Python script from the temporary file
            Process process = Runtime.getRuntime().exec("python " + tempFile.getAbsolutePath());
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Delete the temporary Python file
            tempFile.delete();

            return output.toString();
        } catch (IOException e) {
            return "IOException: " + e.getMessage();
        }
    }

}
