/*
 * Global variables
 */

var brd="................................................................".split("");
var dragobj="";
var mode="Play";


/*
 * Mode
 */

function switchMode(){
	var isEdit=mode!="Edit";
	mode=isEdit?"Edit":"Play";
	document.getElementById('Play').style.display=!isEdit?'Block':'None';
	document.getElementById('Edit').style.display=isEdit?'Block':'None';
	document.getElementById('Mode').innerHTML=mode+' mode';	
}


/*
 * Drag and drop
 */

/**
 * 
 * @param ev
 */
function allowDrop(ev) {
    var to=getSq(getTgt(ev.target));
    if(brd[to].toUpperCase()=="K")
    	return;	// Cannot remove King
    var from=getSq(dragobj);
    var piece=from==-1?dragobj:brd[from];
    if(piece.toUpperCase()=="P" && to!=-1 && (to<8 || to>55))
    	return;	// Cannot place pawn on home areas
    ev.preventDefault();
}

function drag(ev) {
	var tgt=ev.target
	var id=tgt.id?tgt.id:tgt.parentNode.id
    ev.dataTransfer.setData("text", id);
	dragobj=id;
}

function getSq(id){
    return id.startsWith('sq')?id.substring(2)*1:-1;
}

function getTgt(tgt){
	return tgt.id?tgt.id:tgt.parentNode.id;
}

function drop(ev) {
    ev.preventDefault();
    var to=getSq(getTgt(ev.target));
    var from=getSq(dragobj);
    var piece=from==-1?dragobj:brd[from];
    if(from!=-1)
    	fenDel(from);
    if(to!=-1)
    	fenAdd(to,piece);
    update()
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
	var fen= brd2fen(brd)
	  +" "+getTurn()
	  +" "+getCastling()
	  +" "+getEnpassant()
	  +" "+document.getElementById('HALF').value
	  +" "+document.getElementById('MOVES').value;
	document.getElementById('FEN').innerHTML=fen;	  
	return fen; 
}

function getEnpassant(){
	var enp=document.getElementById('ENP');
	return enp.options[enp.selectedIndex].value;
}

function setEnpassant(enp){
	var white=getTurn()=="w"
	var row=white?6:3;
	var options='<option value="-"'+(enp=='-'?' selected':'')+'>-</option>';
	for(var col=0;col<8;col++){
		var lane="ABCDEFGH".charAt(col);
		if(white){
			if(brd[col+32]=='p' && ((col!=0 && brd[col+31]=='P')|| (col!=7 && brd[col+33]=='P') ))
				options+='<option value="'+lane+'6" '+(enp==lane+'6'?' selected':'')+'>'+lane+'-lane</option>';
		} else {
			if(brd[col+24]=='P' && ((col!=0 && brd[col+23]=='p')|| (col!=7 && brd[col+25]=='p') ))
				options+='<option value="'+lane+'3" '+(enp==lane+'3'?' selected':'')+'>'+lane+'-lane</option>';
		}
	}
	document.getElementById('ENP').innerHTML=options;
}

function setFEN(FEN){
	var fen=FEN.split(' ');
	fen2brd(fen[0])
	updateBoard()
	setTurn(fen[1])
	setCastling(fen[2])
	setEnpassant(fen[3])
	document.getElementById('HALF').value=fen[4]
	document.getElementById('MOVES').value=fen[5]
}

function update(){
	setFEN(getFEN());
}

function getTurn(){
	var els=document.getElementsByName("TURN");
	for(var i=0;i<els.length;i++){
		var e=els[i];
		if(e.checked)
			return e.value
	}
	return "?";
}

function setTurn(turn){
	var els=document.getElementsByName("TURN");
	for(var i=0;i<els.length;i++){
		var e=els[i];
		if(e.value==turn)
			e.checked=true
	}
}

function setCastling(cstl){
	document.getElementById('CWK').checked=cstl.indexOf("K")+1
	document.getElementById('CWQ').checked=cstl.indexOf("Q")+1
	document.getElementById('CBK').checked=cstl.indexOf("k")+1
	document.getElementById('CBQ').checked=cstl.indexOf("q")+1
}

function getCastling(){
	var txt="";
	if(document.getElementById('CWK').checked)
		txt+="K";
	if(document.getElementById('CWQ').checked)
		txt+="Q";
	if(document.getElementById('CBK').checked)
		txt+="k";
	if(document.getElementById('CBQ').checked)
		txt+="q";
	return txt?txt:"-";
}

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
