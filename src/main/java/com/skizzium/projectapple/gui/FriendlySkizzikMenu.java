package com.skizzium.projectapple.gui;

import com.skizzium.projectapple.entity.boss.friendlyskizzik.FriendlySkizzik;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class FriendlySkizzikMenu extends AbstractContainerMenu {
    private final Container container;
    private final FriendlySkizzik skizzik;

    public FriendlySkizzikMenu(int id, Inventory inventory, Container container, FriendlySkizzik skizzik) {
        super(null, id);
        this.container = container;
        this.skizzik = skizzik;
        container.startOpen(inventory.player);
        
        this.addSlot(new Slot(container, 0, 8, 18) {
            public boolean mayPlace(ItemStack item) {
                return item.is(Items.SADDLE) && !this.hasItem();
            }
            
            public boolean isActive() {
                return true;
            }
        });
        this.addSlot(new Slot(container, 1, 8, 36) {
            public boolean mayPlace(ItemStack item) {
                return true;
            }

            public boolean isActive() {
                return true;
            }

            public int getMaxStackSize() {
                return 1;
            }
        });

        for(int i = 0; i < 3; ++i) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(inventory, k + i * 9 + 9, 8 + k * 18, 102 + i * 18 - 18));
            }
        }

        for(int j = 0; j < 9; ++j) {
            this.addSlot(new Slot(inventory, j, 8 + j * 18, 142));
        }

    }

    public boolean stillValid(Player player) {
        return !this.skizzik.hasInventoryChanged(this.container) && this.container.stillValid(player) && this.skizzik.isAlive() && this.skizzik.distanceTo(player) < 8.0F;
    }

    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            int i = this.container.getContainerSize();
            if (index < i) {
                if (!this.moveItemStackTo(itemstack1, i, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.getSlot(1).mayPlace(itemstack1) && !this.getSlot(1).hasItem()) {
                if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.getSlot(0).mayPlace(itemstack1)) {
                if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (i <= 2 || !this.moveItemStackTo(itemstack1, 2, i, false)) {
                int j = i + 27;
                int k = j + 9;
                if (index >= j && index < k) {
                    if (!this.moveItemStackTo(itemstack1, i, j, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= i && index < j) {
                    if (!this.moveItemStackTo(itemstack1, j, k, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.moveItemStackTo(itemstack1, j, j, false)) {
                    return ItemStack.EMPTY;
                }

                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    public void removed(Player player) {
        super.removed(player);
        this.container.stopOpen(player);
    }
}
