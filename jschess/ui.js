/**
 * 
 */

function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
	var tgt=ev.target
	var td=tgt.parentNode
    ev.dataTransfer.setData("text", td.id.substring(2));
}

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text")*1;
    var piece=fenDel(data);
    fenAdd(ev.target.id.substring(2)*1,piece);
    updateBoard();
    getFEN()
}

/* **********************************************************
 * Drawing the board
 ********************************************************** */

/** 
 * Fills the HTML for the 
 * @returns {String}
 */
function createBoard(){
	var txt="";
	var ch="<tr>";
	for(c=0;c<10;c++)
		ch+='<td class="hdr">'+" ABCDEFGH ".charAt(c)+'</td>';
	ch+='</tr>';
	for(r=0;r<8;r++){
		var rh='<td class="hdr">'+"87654321".charAt(r)+'</td>';
		txt+='<tr>'+rh;
		for(c=0;c<8;c++){
			txt+='<td class="'+((c+r)%2==1?"blk":"wht") +'" id="sq'+((7-r)*8+c)+'" ondrop="drop(event)" ondragover="allowDrop(event)"></td>';
		}
		txt+=rh+'</tr>';
	}
	return '<table id="brd">'+ch+txt+ch+'</table>';
}

/* **********************************************************
 * Drawing the pieces
 ********************************************************** */

function initBoard(){
	var fen=document.getElementById('FEN').innerHTML;
	setFEN(fen);
}


var IMG_PCS={'K':'wk','k':'bk','Q':'wq','q':'bq','R':'wr','r':'br','N':'wn','n':'bn','B':'wb','b':'bb','P':'wp','p':'bp'};

/**
 * Adds piece to square
 * @param sq
 * @param piece
 */
function fenAdd(sq, piece){
	brd[sq]=piece;
}

/**
 * Removes piece from square
 * @param sq
 * @returns {String}
 */
function fenDel(sq){
	var c=brd[sq];
	brd[sq]=".";
	return c;
}

function getFEN(){
	var els=document.getElementsByName("TURN");
	var turn="?";
	for(var i=0;i<els.length;i++){
		var e=els[i];
		if(e.checked)
			turn=e.value
	}
	document.getElementById('FEN').innerHTML= brd2fen(brd)
	  +" "+turn
	  +" "+document.getElementById('CSTL').innerHTML
	  +" "+document.getElementById('ENP').innerHTML
	  +" "+document.getElementById('HALF').value
	  +" "+document.getElementById('MOVES').value
}

function setFEN(FEN){
	var fen=FEN.split(' ');
	fen2brd(fen[0])
	updateBoard()
	var els=document.getElementsByName("TURN");
	for(var i=0;i<els.length;i++){
		var e=els[i];
		if(e.value==fen[1])
			e.checked=true
	}
	document.getElementById('CSTL').innerHTML=fen[2]
	document.getElementById('ENP').innerHTML=fen[3]
	document.getElementById('HALF').value=fen[4]
	document.getElementById('MOVES').value=fen[5]
}

var brd="................................................................".split("")

function fen2brd(fen){
	var n=fen.length
	var row=7;
	var col=0;
	for (var i = 0; i < n; i++) {
		var c=fen.charAt(i);
		if(c>'0' && c<='9'){
			col+=(c*1);
		} else if(c=='/'){
			row--;
			col=0;
		} else {
			brd[row*8+col]=c;
			col++;
		}
	}
}

function brd2fen(brd){
	var txt="";
	for (var row = 7; row >= 0; row--) {
		var empty=0;
		for (var col = 0; col < 8; col++) {
			var c=brd[row*8+col];
			if(c=='.'){
				empty++;
			} else {
				if(empty){
					txt+=empty;
					empty=0;
				}
				txt+=c;
			}
		}
		if(empty){
			txt+=empty;
			empty=0;
		}
		if(row)
			txt+='/';
	}
	return txt;
}


/**
 * 
 * @param brd
 */
function updateBoard(){
	for (var i = 0; i < 64; i++) {
		var c = brd[i];
		var sq=document.getElementById("sq"+i);
		sq.innerHTML=c=='.'?'':'<img src="img/'+IMG_PCS[c]+'.gif" draggable="true" ondragstart="drag(event)"/>';
	}
}
