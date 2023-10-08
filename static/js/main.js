jQuery(document).ready(function () {
    var codeEditorContainer = jQuery("#codeEditor");
    var editor = CodeMirror(codeEditorContainer[0], {
        mode: "javascript",
        theme: "default",
        lineNumbers: true,
        autofocus: true,
    });
});
