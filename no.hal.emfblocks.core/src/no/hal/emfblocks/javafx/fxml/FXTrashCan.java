package no.hal.emfblocks.javafx.fxml;

import java.io.IOException;
import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import no.hal.emfblocks.WRoot;
import no.hal.emfblocks.WTrashCan;
import no.hal.emfblocks.javafx.FXUtil;

public class FXTrashCan extends Pane implements WPaneUI
{
	private WTrashCan model;
	
	/**Create an FXTrashCan without a model object. 
	 * The model object must be assigned later. There are two mechanisms for this.
	 * <ul>
	 * <li>The model object can be automatically generated by calling {@link #getModel(WRoot)}</li>
	 * <li>The model object can be explicitly set by calling {@link #setModel(WTrashCan)}</li>
	 * </ul>
	 * If {@link WRoot#installContentPane(javafx.scene.Parent)} is called and this object is in the node structure, 
	 * then model objects are automatically created using the first mechanism, unless they have already been assigned.*/
	public FXTrashCan()
	{
		this(null);
	}
	/**Create an FXTrashCan and immediately assign the model object.*/
	public FXTrashCan(WTrashCan model)
	{
		super();
		if(model != null)
			setModel(model);
		
		try
		{
			InputStream stream = getClass().getResourceAsStream("GARBAGE.gif");
			if(stream == null)
				throw new IOException("Trash can image does not exist.");
			Image im = new Image(stream);
			
			ImageView imV = new ImageView(im);
			double w = 240;
			double h = w*im.getHeight()/im.getWidth();
			imV.fitWidthProperty().set(w);
			imV.fitHeightProperty().set(h);
			getChildren().add(imV);	
			this.setMinWidth(w);
			this.setMinHeight(h);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		FXUtil.setBackground(this, Color.WHITE);
	}
	/**Set the model object to the indicated instance.*/
	public void setModel(WTrashCan m)
	{
		if(model != null)
			new Throwable("Warning: "+this+" already has its model set to: "+model+"!!");
		model = m;
	}
	@Override
	public WTrashCan getModel(WRoot root)
	{
		if(model == null)
		{
			WTrashCan m = new WTrashCan(root, this);
			//root.add(m);
			setModel(m);
		}
		else
			assert model.getRoot() == root : this+" was assigned to root "+root+", but the model's root is "+model.getRoot()+"!";
		return model;
	}
}
