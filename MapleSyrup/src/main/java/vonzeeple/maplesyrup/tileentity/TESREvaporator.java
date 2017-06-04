package vonzeeple.maplesyrup.tileentity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fluids.FluidStack;

public class TESREvaporator extends TileEntitySpecialRenderer<TileEntityEvaporator>{
	
	@Override
	public void renderTileEntityAt(TileEntityEvaporator te, double x, double y, double z, float partialTicks, int destroyStage) {


		Tessellator tes=Tessellator.getInstance();
		VertexBuffer wr = tes.getBuffer();
	    GlStateManager.pushMatrix();
	    
	    
	    
		//New rendering, level in cauldron
		
	    //float h=1f;
	    //float w=2f/16f;
	    float yoffset=4f/16f;
	    
		GlStateManager.enableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.color(1f,1f,1f,1);
		GlStateManager.enableLighting();
		

		
		FluidStack fluid=te.getFluidStack();
		if(fluid!=null){
	    Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
	    TextureAtlasSprite sprite =  Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(fluid.getFluid().getStill().toString());

	    if(sprite != null)
	    {
	    
		wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);


	    float h2=(1-yoffset)*te.getLevelRatio()+yoffset;
	    GlStateManager.translate(x, y, z);
		wr.pos(0f, h2, 0f).tex(sprite.getMaxU(), sprite.getMinV()).endVertex();
		wr.pos(0f, h2, 1f).tex(sprite.getMaxU(), sprite.getMaxV()).endVertex();
		wr.pos(1f, h2, 1f).tex(sprite.getMinU(), sprite.getMaxV()).endVertex();		
		wr.pos(1f, h2, 0f).tex(sprite.getMinU(), sprite.getMinV()).endVertex();		
		
		tes.draw();

	    }
		}	    
	    
	    
	    
		//Old rendering with gauge in front
		/*	    
	    
	    
	    GlStateManager.translate(x+0.5, y+0.5, z+0.5);
	    
	    //Do not call this every time, store the value in the tile entity
	    
		switch (te.getBlockState().getValue(BlockHorizontal.FACING))
        {
			
            case NORTH:
            	GlStateManager.rotate(0f, 0f, 1f, 0f);
           	 break;
            case SOUTH:
            	GlStateManager.rotate(180f, 0f, 1f, 0f);
           	 break;
            case WEST:
            	GlStateManager.rotate(90f, 0f, 1f, 0f);
           	 break;
            case EAST:
            default:
            	GlStateManager.rotate(270f, 0f, 1f, 0f);
        }

		

		GlStateManager.shadeModel(GL11.GL_FLAT);		
	    float h=12f/16f;
	    float w=2f/16f;
	    GlStateManager.translate(0f, 0f, 0.5001f);    
		//Back of the gauge


		GlStateManager.color(0f,0f,0f,1);
		GlStateManager.disableLighting();
		GlStateManager.disableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.disableTexture2D();	
	    wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
	    

		wr.pos(w/2f, -h/2f, 0f).color(0f, 0f, 0f, 1f).endVertex();
		wr.pos(w/2f, h/2f, 0f).color(0f, 0f, 0f, 1f).endVertex();
		wr.pos(-w/2f, h/2f, 0f).color(0f, 0f, 0f, 1f).endVertex();		
		wr.pos(-w/2f, -h/2f, 0f).color(0f, 0f, 0f, 1f).endVertex();	
		
		tes.draw();    

		
		//Drawing the fluid
		GlStateManager.enableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.color(1f,1f,1f,1);
		GlStateManager.enableLighting();
		
		GlStateManager.translate(0f, 0f, 0.0001f);
		
		FluidStack fluid=te.getFluidStack();
		if(fluid!=null){
	    Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
	    TextureAtlasSprite sprite =  Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(fluid.getFluid().getStill().toString());

	    if(sprite != null)
	    {
	    
		wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);


	    float h2=h*te.getLevelRatio();

		wr.pos(w/2f, -h/2f, 0f).tex(sprite.getMaxU(), sprite.getMinV()).endVertex();
		wr.pos(w/2f, -h/2f+h2, 0f).tex(sprite.getMaxU(), sprite.getMaxV()).endVertex();
		wr.pos(-w/2f, -h/2f+h2, 0f).tex(sprite.getMinU(), sprite.getMaxV()).endVertex();		
		wr.pos(-w/2f, -h/2f, 0f).tex(sprite.getMinU(), sprite.getMinV()).endVertex();		
		
		tes.draw();

	    }
		}
	    	    */

	    
	    GlStateManager.popMatrix();
	    
	}


}
