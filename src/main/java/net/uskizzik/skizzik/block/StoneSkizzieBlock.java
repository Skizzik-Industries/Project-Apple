
package net.uskizzik.skizzik.block;

import net.uskizzik.skizzik.itemgroup.TemplateTabItemGroup;
import net.uskizzik.skizzik.SkizzikModElements;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.Mirror;
import net.minecraft.util.Direction;
import net.minecraft.state.StateContainer;
import net.minecraft.state.DirectionProperty;
import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.BlockItem;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import java.util.List;
import java.util.Collections;

@SkizzikModElements.ModElement.Tag
public class StoneSkizzieBlock extends SkizzikModElements.ModElement {
	@ObjectHolder("skizzik:stone_skizzie")
	public static final Block block = null;
	public StoneSkizzieBlock(SkizzikModElements instance) {
		super(instance, 12);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(TemplateTabItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getCutoutMipped());
	}
	public static class CustomBlock extends Block {
		public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
		public CustomBlock() {
			super(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1.5f, 6f).setLightLevel(s -> 0).harvestLevel(-1)
					.harvestTool(ToolType.PICKAXE).setRequiresTool().notSolid().setOpaque((bs, br, bp) -> false));
			this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
			setRegistryName("stone_skizzie");
		}

		@Override
		public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
			return true;
		}

		@Override
		public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
			Vector3d offset = state.getOffset(world, pos);
			switch ((Direction) state.get(FACING)) {
				case SOUTH :
				default :
					return VoxelShapes.or(makeCuboidShape(9.5, 2.1, 10.5, 6.5, 17.1, 7.5), makeCuboidShape(12, 17, 14, 4, 25, 6),
							makeCuboidShape(13.5, 13.6, 10, 2.5, 15.6, 8), makeCuboidShape(13.5, 11.1, 10, 2.5, 13.1, 8),
							makeCuboidShape(13.5, 8.6, 10, 2.5, 10.6, 8)).withOffset(offset.x, offset.y, offset.z);
				case NORTH :
					return VoxelShapes.or(makeCuboidShape(6.5, 2.1, 5.5, 9.5, 17.1, 8.5), makeCuboidShape(4, 17, 2, 12, 25, 10),
							makeCuboidShape(2.5, 13.6, 6, 13.5, 15.6, 8), makeCuboidShape(2.5, 11.1, 6, 13.5, 13.1, 8),
							makeCuboidShape(2.5, 8.6, 6, 13.5, 10.6, 8)).withOffset(offset.x, offset.y, offset.z);
				case EAST :
					return VoxelShapes.or(makeCuboidShape(10.5, 2.1, 6.5, 7.5, 17.1, 9.5), makeCuboidShape(14, 17, 4, 6, 25, 12),
							makeCuboidShape(10, 13.6, 2.5, 8, 15.6, 13.5), makeCuboidShape(10, 11.1, 2.5, 8, 13.1, 13.5),
							makeCuboidShape(10, 8.6, 2.5, 8, 10.6, 13.5)).withOffset(offset.x, offset.y, offset.z);
				case WEST :
					return VoxelShapes.or(makeCuboidShape(5.5, 2.1, 9.5, 8.5, 17.1, 6.5), makeCuboidShape(2, 17, 12, 10, 25, 4),
							makeCuboidShape(6, 13.6, 13.5, 8, 15.6, 2.5), makeCuboidShape(6, 11.1, 13.5, 8, 13.1, 2.5),
							makeCuboidShape(6, 8.6, 13.5, 8, 10.6, 2.5)).withOffset(offset.x, offset.y, offset.z);
			}
		}

		@Override
		protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
			builder.add(FACING);
		}

		public BlockState rotate(BlockState state, Rotation rot) {
			return state.with(FACING, rot.rotate(state.get(FACING)));
		}

		public BlockState mirror(BlockState state, Mirror mirrorIn) {
			return state.rotate(mirrorIn.toRotation(state.get(FACING)));
		}

		@Override
		public BlockState getStateForPlacement(BlockItemUseContext context) {
			;
			return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
		}

		@Override
		public MaterialColor getMaterialColor() {
			return MaterialColor.AIR;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
