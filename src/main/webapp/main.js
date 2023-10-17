var codeEditorContainer;
var editor;

jQuery(document).ready(function () {
    codeEditorContainer = jQuery("#codeEditor");
    initializeEditor("text/x-java"); // Initialize with Java mode

    jQuery("#languageSelector").change(function () {
        var selectedLanguage = jQuery(this).val();
        changeEditorMode(selectedLanguage);
        var classNameInput = jQuery("#classNameInput");
        if (selectedLanguage === "java") {
            classNameInput.show();
        } else {
            classNameInput.hide();
        }
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

function codeCompile(){
	var code = editor.getValue();
    var selectedLanguage = jQuery("#languageSelector").val();
    console.log(selectedLanguage);
    var className = "";

    if (selectedLanguage === "java") {
        // Get the class name if Java is selected
        className = jQuery("#classNameInput").val();
        if(className==""){
			alert("Class Name cannot be empty in java")
			return;
		}
        
    }

    console.log(selectedLanguage);
    console.log(className);
    return jQuery.ajax({
        type: "POST",
        url: contextPath + module, // Specify the URL of your backend servlet
        data: {
			action : "compileCode",
            code: code, // Send the code to the server
            className,
            language: selectedLanguage, // Include the selected programming language
        },
        success: function (response) {
            // Handle the response from the server (e.g., display output in the output-area)
            jQuery("#output-area").html(response);
            console.log("hey"+ response);
        },
        error: function (error) {
            // Handle errors if the server request fails
            console.error("Error:", error);
        },
    });
   
}