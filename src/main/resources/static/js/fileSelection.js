

let dataTransfer = new DataTransfer()
let sizeValidFlag = true;
let filesArray = [];

function fileSelection(fileInputId,fileUploadId,selectFileId,type) {
    console.log("start")
    const fileInput = document.getElementById(fileInputId)
    const fileUpload = document.getElementById(fileUploadId)

    let notAdd = false;
    let id = "#"+fileUploadId

    $(id).on('change', function(e){
        console.log("value"+fileUpload.value)
        console.log(dataTransfer)
        console.log(dataTransfer.items)
        console.log("onchange")
        for (const file of e.target.files) {
            new Promise(resolve => {
                console.log("resolve")
                for (let i = 0; i < e.target.files.length; i++) {
                    for(let j = 0; j < dataTransfer.files.length; j++){
                        if(dataTransfer.files[j].name == e.target.files[i].name){
                            notAdd = true;
                        }
                    }
                    if(!notAdd){
                        dataTransfer.items.add(e.target.files[i])
                    }
                    notAdd = false;
                    if(type == "table"){
                        console.log("apppend")
                        appendElement(selectFileId)
                    }else{
                        appendDivElement(selectFileId)
                    }
                }
            })
        }
        sizeValidFlag = checkFilesSize(dataTransfer.files)
        fileUpload.files=dataTransfer.files

    });

}

function fileSelectionDocument(fileInputId,fileUploadId,selectFileId,type) {
    console.log("start")
    const fileUpload = document.getElementById(fileUploadId)

    let savedFileNameList = document.getElementById("exportDocumentNameList").value.replace('[', '').replace(']','').split(",");
    let notAdd = false;
    let id = "#"+fileUploadId

    $(id).on('change', function(e){
        for (const file of e.target.files) {
            new Promise(resolve => {
                console.log("resolve")
                for (let i = 0; i < e.target.files.length; i++) {
                    for(let j = 0; j < dataTransfer.files.length; j++){
                        if(dataTransfer.files[j].name == e.target.files[i].name){
                            notAdd = true;
                        }
                    }
                    if(!notAdd){
                        let duplicateCheck = duplicateFileCheck(savedFileNameList,e.target.files[i].name.toString().toUpperCase())
                        if(duplicateCheck){
                            if(bytesToMB(e.target.files[i].size) < 25){
                                dataTransfer.items.add(e.target.files[i])
                            }else{
                                if(languageFlag == 0){
                                    showSystemError("サイズが 25MB を超えるファイルはアップロードできません")
                                }else{
                                    showSystemError("Files larger than 25MB in size can not be uploaded.")
                                }
                                break
                            }
                        }else{
                            if(languageFlag == 0){
                                showSystemError("アップロードファイルは既に存在します")
                            }else{
                                showSystemError("Uploaded file is already existed.")
                            }
                            break;
                        }
                    }
                    notAdd = false;

                    if(type == "table"){
                        console.log("apppend")
                        if(checkFilesSize(dataTransfer.files)){
                            appendElement(selectFileId)
                        }
                    }else{
                        if(checkFilesSize(dataTransfer.files)){
                            appendDivElement(selectFileId)
                        }
                    }
                }
            })
        }
        // sizeValidFlag = checkFilesSize(dataTransfer.files)
            submitSaveDocumentFiles()
            fileUpload.files=dataTransfer.files
    });

}

function duplicateFileCheck(savedFiles,selectFile){
    let returnType = true
    for(let i=0;i<savedFiles.length;i++){
        let file = savedFiles[i].toString().toUpperCase()
        if(file.trim() == selectFile){
            returnType =  false
            break;
        }
    }
    return returnType
}

function checkFilesSize(files){
    let totalSize = 0.0
    Array.from(files).forEach(file => {
        let sizeInMB= bytesToMB(file.size)
        totalSize += sizeInMB
    });
    return totalSize <= 25;
}

function bytesToMB(bytes) {
    let kb = bytes/1024;
    let mb = kb/1024;
    return mb;
}

function appendElement(selectFileId){
    console.log("this is append fun")
    $("#"+selectFileId).empty()
    for(let i = 0; i < dataTransfer.files.length; i++){
        let fileName =dataTransfer.files[i].name
        $("#"+selectFileId).append("<tr><th>"+ fileName +"</th><th><a id=remove"+i+" style='cursor: pointer'><i class='bi bi-trash text-danger'></i></a></th> </tr>")
        let removeElem = document.getElementById("remove"+i)
        removeElem.onclick = function() { fileRemove(i,selectFileId)};
    }


}

function appendDivElement(selectFileId){
    $("#"+selectFileId).empty()
    for(let i = 0; i < dataTransfer.files.length; i++){
        let fileName =dataTransfer.files[i].name
        $("#"+selectFileId).append("<div>"+ fileName +"</div><a id=remove"+i+" style='cursor: pointer'><i class='bi bi-trash text-danger'></i></a></></tr>")
        let removeElem = document.getElementById("remove"+i)
        removeElem.onclick = function() {fileDivRemove(i,selectFileId)};
    }
}

function fileRemove(index,selectFileId) {
    dataTransfer.items.remove(index)
    appendElement(selectFileId)
}

function fileDivRemove(index,selectFileId){
    dataTransfer.items.remove(index)
    appendDivElement(selectFileId)
}



function save(){
    console.log("this is save")
    console.log(filesArray)
    let element = document.getElementById("documentFilesLists")
    element.value= filesArray
    console.log(filesArray)
    console.log("element value")
    console.log(element.value)

    // let value = exportRegistrationCheck();
    // alert(value);
    if(dataTransfer.items.length != 0){
        document.getElementById("saveExportDocument").submit()
    }else{
       showSystemError("選択ファイルがありません。")
    }

}

function submitSaveDocumentFiles(){
    let nameArray = [];
    let fileSizeArray = [];
    getFiles().then(function (fileArray){
        nameArray = getFileNames()
        fileSizeArray = getFileSizes()
        console.log("this is nameArr")
        console.log(nameArray)
        console.log("----------")
        console.log("this fileArray")
        console.log(fileArray)
        console.log("---------")
        filesArray = fileArray
        document.getElementById(("documentFilesNames")).value = nameArray
        document.getElementById(("documentFilesSizes")).value = fileSizeArray

    })
}

async function getFiles(){
    let fileList  = dataTransfer.files;
    let files = [];
    for (const file of Array.from(fileList)) {
        let reader = new FileReader();

        reader.addEventListener("load",() => {
            var arrayBuffer = reader.result.toString()
            files.push(arrayBuffer);
        });
        await reader.readAsDataURL(file);
    }
    return files
}
function getFileNames(){
    let fileList  = dataTransfer.files;
    let names = [];

    for (const file of Array.from(fileList)) {
        let reader = new FileReader();
        names.push(file.name.toString())
    }

    return names
}

function getFileSizes(){
    let fileList  = dataTransfer.files;
    let filesSize = []

    for (const file of Array.from(fileList)) {
        let size = bytesToMB(file.size)
        filesSize.push(size)
    }

    return filesSize
}

function downloadDocument(invoiceCode,documentId){
    $.get('/getDocumentDownloadUrl',
        {invoiceCode:invoiceCode,
            documentId:documentId}, function(file){
            const linkSource = file.url
            const downloadLink = document.createElement("a");
            downloadLink.href = linkSource;
            downloadLink.download =file.fileName;
            downloadLink.click();
        })
}



