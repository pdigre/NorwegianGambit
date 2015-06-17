/**
 * 
 */

document.write(createBoard());

function createBoard(){
	var txt="<table>";
	for(r=0;r<8;r++){
		txt+="<tr>";
		for(c=0;c<8;c++){
			txt+='<td class="'+((c+r)%2==1?"blk":"wht") +'"></td>';
		}
		txt+="</tr>";
	}
	return txt+"</table>";
}