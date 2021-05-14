package com.example.donors.library;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J@\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J \u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u0013H\u0016J\u0018\u0010\u001d\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0018H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/example/donors/library/SwipeToDelete;", "Landroidx/recyclerview/widget/ItemTouchHelper$SimpleCallback;", "adapter", "Lcom/example/donors/adapter/ActivityMainRVAdapter;", "(Lcom/example/donors/adapter/ActivityMainRVAdapter;)V", "getAdapter", "()Lcom/example/donors/adapter/ActivityMainRVAdapter;", "setAdapter", "deleteIcon", "Landroid/graphics/drawable/Drawable;", "swipeBackgroundDelete", "Landroid/graphics/drawable/ColorDrawable;", "onChildDraw", "", "c", "Landroid/graphics/Canvas;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "dX", "", "dY", "actionState", "", "isCurrentlyActive", "", "onMove", "target", "onSwiped", "direction", "app_debug"})
public final class SwipeToDelete extends androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback {
    private android.graphics.drawable.Drawable deleteIcon;
    private android.graphics.drawable.ColorDrawable swipeBackgroundDelete;
    @org.jetbrains.annotations.NotNull()
    private com.example.donors.adapter.ActivityMainRVAdapter adapter;
    
    @java.lang.Override()
    public boolean onMove(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView, @org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView.ViewHolder viewHolder, @org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView.ViewHolder target) {
        return false;
    }
    
    @java.lang.Override()
    public void onSwiped(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView.ViewHolder viewHolder, int direction) {
    }
    
    @java.lang.Override()
    public void onChildDraw(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas c, @org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView, @org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.donors.adapter.ActivityMainRVAdapter getAdapter() {
        return null;
    }
    
    public final void setAdapter(@org.jetbrains.annotations.NotNull()
    com.example.donors.adapter.ActivityMainRVAdapter p0) {
    }
    
    public SwipeToDelete(@org.jetbrains.annotations.NotNull()
    com.example.donors.adapter.ActivityMainRVAdapter adapter) {
        super(0, 0);
    }
}