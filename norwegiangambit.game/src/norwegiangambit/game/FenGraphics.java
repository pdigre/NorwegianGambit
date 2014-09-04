package norwegiangambit.game;

import java.util.EnumMap;

import norwegiangambit.util.PieceType;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

public class FenGraphics {

	public static FenGraphics graphics = new FenGraphics();

	public FenGraphics() {
		super();
		loadImages(getClass());
	}

	public EnumMap<PieceType, ImageData> imgdatas = new EnumMap<PieceType, ImageData>(PieceType.class);

	public EnumMap<PieceType, Image> images = new EnumMap<PieceType, Image>(PieceType.class);

	public Image board;

	public ImageData boarddata;

	private void loadImages(Class<?> clz) {
		for (PieceType ptype : PieceType.values()) {
			String filename = (ptype.fen > 'Z' ? "b" + ptype.fen + ".png" : "w" + ptype.fen + ".png").toLowerCase();
			ImageData imgdata = new ImageData(clz.getClassLoader().getResourceAsStream(filename));
			imgdatas.put(ptype, imgdata);
		}
		boarddata = new ImageData(clz.getClassLoader().getResourceAsStream("brd.png"));
	}

	public Image getBoardImg(Device device) {
		if (board == null)
			board = new Image(device, boarddata);
		return board;
	}

	public Image getImage(int piece, Device device) {
		if (images.isEmpty()) {
			for (PieceType pt : PieceType.values()) {
				images.put(pt, new Image(device, imgdatas.get(pt)));
			}
		}
		return images.get(PieceType.types[piece]);
	}

}
