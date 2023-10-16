var codeEditorContainer;
var editor;

jQuery(document).ready(function () {
    codeEditorContainer = jQuery("#codeEditor");
    initializeEditor("text/x-java"); // Initialize with Java mode

    jQuery("#languageSelector").change(function () {
        var selectedLanguage = jQuery(this).val();
        changeEditorMode(selectedLanguage);
    });

    function initializeEditor(mode) {
        editor = CodeMirror(codeEditorContainer[0], {
            mode: mode,
            theme: "default",
            lineNumbers: true,
            autofocus: true,
        });
    }

    function changeEditorMode(selectedLanguage) {
        var modeMapping = {
            java: "text/x-java",
            python: "text/x-python",
        };

        var selectedMode = modeMapping[selectedLanguage] || "text/plain";

        // Clear the current editor instance and reinitialize with the new mode
        codeEditorContainer.empty();
        initializeEditor(selectedMode);
    }
});
