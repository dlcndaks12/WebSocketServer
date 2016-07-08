var support;

/* webSocket 지원 브라우저 확인 */
if (window.WebSocket){
	support = true;
	console.log("BROWSER SUPPORTED");
} else {
	support = false;
	console.log("BROWSER NOT SUPPORTED");
}

/* ================================================================== */ 
  
var fileWebSocket = null;
var bi = 0;
var file = null;
var fs;
var statusArea;
var upFile = 0;

function fileConnect() {
	fileWebSocket = new WebSocket("ws://192.168.0.186:19080/fileUpload");
	fileWebSocket.binaryType="blob";
	fileWebSocket.onopen = function() {
		statusArea.text("Connected ...");
	}
	
	fileWebSocket.onmessage = function(e) {
		bi += parseInt(e.data);
		
		var progress = (bi/file[upFile].size) * 100;
		$(".file_list > li .progress").eq(upFile).children(".bar").css("width", progress+"%");
		
		if(fs.currentSlice < fs.slices ) {
			fileWebSocket.send(fs.getNextSlice());
		} else {
			
			if (progress == 100) {
				fileWebSocket.send("end");
				bi = 0;
				upFile++;
				if(upFile < file.length) {
					console.log("더있어요");
					fileSend();
				}
			}
		}
	}
	
	fileWebSocket.onclose = function() {
		statusArea.text("Disconnected ...");
	}
	
	fileWebSocket.onerror = function(e) {
		statusArea.text(e.msg);
	}
}
function clickSend() {
	upFile = 0;
	fileSend();
}
function fileSend() {
	$(".file_list > li .progress").show();
	
	fileWebSocket.send(file[upFile].name);
	console.log(file[upFile].name);
	fs = new FileSlicer(file[upFile]);
	fileWebSocket.send(fs.getNextSlice());
}

function fileDisconnect() {
	fileWebSocket.close();
}

function FileSlicer(file) {
    this.sliceSize = 1024;  
    this.slices = Math.ceil(file.size / this.sliceSize);

    this.currentSlice = 0;

    this.getNextSlice = function() {
        var start = this.currentSlice * this.sliceSize;
        var end = Math.min((this.currentSlice+1) * this.sliceSize, file.size);
        ++this.currentSlice;

        return file.slice(start, end);
    }
}

function addFile() {
	$(".file_list").empty();
	
	for(var i=0; i<file.length; i++) {
		var item =  "<li>";
		    item += file[i].name;
		    item += "<div class='progress'><span class='bar'></span></div>";
		    item += "</li>";
		$(".file_list").append(item);
	}
}

/* ================== Drag & Drop 관련 ================= */

$(document).ready(function() {
	statusArea = $(".statusArea");
});

$(document).on("dragenter",".upload",function(e){
    e.stopPropagation();
    e.preventDefault();
    $(this).css('border', '2px solid #0B85A1');
});
$(document).on("dragover",".upload",function(e){
    e.stopPropagation();
    e.preventDefault();
});

/* File 정보를 읽어오는 부분 */
$(document).on("drop",".upload",function(e){
    $(this).css('border', '2px dotted #0B85A1');
    e.preventDefault();
    file = e.originalEvent.dataTransfer.files;
    
    addFile();
});
$(document).on("change", "#file", function(e) {
	file = this.files;

	addFile();
});


$(document).on('dragenter', function (e){
    e.stopPropagation();
    e.preventDefault();
});
$(document).on('dragover', function (e){
  	e.stopPropagation();
  	e.preventDefault();
  	$(".upload").css('border', '2px dotted #0B85A1');
});
$(document).on('drop', function (e){
    e.stopPropagation();
    e.preventDefault();
});