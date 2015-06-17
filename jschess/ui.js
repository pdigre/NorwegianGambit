/**
 * 
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
			txt+='<td class="'+((c+r)%2==1?"blk":"wht") +'" id="sq'+((7-r)*8+c)+'"></td>';
		}
		txt+=rh+'</tr>';
	}
	return '<table id="brd" class="brd">'+ch+txt+ch+'</table>';
}

function setFEN(FEN){
	var sq=document.getElementById("sq1");
	sq.className=sq.className+" K";
}