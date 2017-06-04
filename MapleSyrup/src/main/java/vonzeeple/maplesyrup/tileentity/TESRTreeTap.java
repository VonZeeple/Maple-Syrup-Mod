package vonzeeple.maplesyrup.tileentity;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.ImmutableMap;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.client.model.IRetexturableModel;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import vonzeeple.maplesyrup.MapleSyrup;
import vonzeeple.maplesyrup.api.PropertiesHandler;
import vonzeeple.maplesyrup.common.Content;
import vonzeeple.maplesyrup.util.Logger;

public class TESRTreeTap extends TileEntitySpecialRenderer<TileEntityTreeTap> {
	
	@Override
	public void renderTileEntityAt(TileEntityTreeTap te, double x, double y, double z, float partialTicks, int destroyStage) {

		
		//final BlockRendererDispatcher blockRenderer = Minecraft.getMinecraft().getBlockRendererDispatcher();
		BlockPos blockPos = te.getPos();
		IBlockState state = getWorld().getBlockState(blockPos);
		if(state.getBlock() != Content.treeTap)
			return;



		Tessellator tes=Tessellator.getInstance();
		VertexBuffer wr = tes.getBuffer();
		
	    GlStateManager.pushMatrix();

	    
	    
	    
	    GlStateManager.enableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.color(1f,1f,1f,1);
		GlStateManager.enableLighting();
	    GlStateManager.translate(x, y, z);		
	    GlStateManager.translate(0.5, 0, 0.5);
	    
		switch (state.getValue(BlockHorizontal.FACING))
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
	    
	    GlStateManager.translate(0, -0.01, 0.2);	    
		
	    //need to sync client and server side
		//Fluid fluid=te.getFluid();
		Fluid fluid=Content.fluidMapleSap;
		if(fluid!=null){
	    Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
	    TextureAtlasSprite sprite =  Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(fluid.getStill().toString());

	    float d=0.3f;
	    float h=0.5f;
	    if(sprite != null)
	    {
	    
		wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		wr.pos(-d/2, h, -d/2).tex(sprite.getMaxU(), sprite.getMinV()).endVertex();
		wr.pos(-d/2, h, d/2).tex(sprite.getMaxU(), sprite.getMaxV()).endVertex();
		wr.pos(d/2, h, d/2).tex(sprite.getMinU(), sprite.getMaxV()).endVertex();		
		wr.pos(d/2, h, -d/2).tex(sprite.getMinU(), sprite.getMinV()).endVertex();		
		
		tes.draw();

	    }
		}	    
	    
	    /*
	    state = state.getActualState(getWorld(), blockPos).withProperty(PropertiesHandler.LIQUIDRENDER, true);
		IBakedModel model = blockRenderer.getBlockModelShapes().getModelForState(state);
		RenderHelper.disableStandardItemLighting();
		GlStateManager.blendFunc(770, 771);
		GlStateManager.enableBlend();
		GlStateManager.disableCull();
		if(Minecraft.isAmbientOcclusionEnabled())
			GlStateManager.shadeModel(7425);
		else
			GlStateManager.shadeModel(7424);
	    
		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		Minecraft.getMinecraft().getTextureManager().bindTexture(Content.fluidMapleSyrup.getStill());
		TextureAtlasSprite fluidIcon = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(Content.fluidMapleSyrup.getStill().toString());
	    wr.setTranslation( -blockPos.getX(), - blockPos.getY(), -blockPos.getZ());
		wr.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);

		
		blockRenderer.getBlockModelRenderer().renderModel(te.getWorld(), model, state, blockPos, wr,true);
		tes.draw();		
		wr.setTranslation(0.0D, 0.0D, 0.0D);
		
		RenderHelper.enableStandardItemLighting();
		*/
		
		
		
		
		GlStateManager.popMatrix();		
	}

}
