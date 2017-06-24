/*
 Copyright (c) 2012 Roman Truba

 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial
 portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
 THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package we.video.wevideo.ui.support.touchGallery.adater;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import we.video.wevideo.ui.support.touchGallery.view.GalleryViewPager;

/**
 * Class wraps URLs to adapter, then it instantiates <b>UrlTouchImageView</b>
 * objects to paging up through them.
 */
public class BasePagerAdapter extends PagerAdapter {

	protected List<String> mResources = new ArrayList<String>();
	protected final Context mContext;
	protected int mCurrentPosition = -1;
	protected OnItemChangeListener mOnItemChangeListener;
	protected OnItemClickListener mOnItemClickListener;

	public BasePagerAdapter() {
		mContext = null;
	}

	public BasePagerAdapter(Context context) {
		this.mContext = context;
	}

	public List<String> getData(){
		return this.mResources;
	}

	@Override
	public void setPrimaryItem(ViewGroup container, final int position,
			Object object) {
		super.setPrimaryItem(container, position, object);
		if (mCurrentPosition == position)
			return;
		GalleryViewPager galleryContainer = ((GalleryViewPager) container);
		if (galleryContainer.mCurrentView != null) {
			galleryContainer.mCurrentView.resetScale();
		}
		mCurrentPosition = position;
		if (mOnItemChangeListener != null)
			mOnItemChangeListener.onItemChange(mCurrentPosition);
	}

	@Override
	public void destroyItem(ViewGroup collection, int position, Object view) {
		collection.removeView((View) view);
	}

	@Override
	public int getCount() {
		return mResources.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view.equals(object);
	}

	@Override
	public void finishUpdate(ViewGroup arg0) {
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {
	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	@Override
	public void startUpdate(ViewGroup arg0) {
	}

	public int getCurrentPosition() {
		return mCurrentPosition;
	}

	public void setOnItemChangeListener(OnItemChangeListener listener) {
		mOnItemChangeListener = listener;
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		mOnItemClickListener = listener;
	}

	public OnItemClickListener getOnItemClickListener() {
		return mOnItemClickListener;
	}

	public static interface OnItemChangeListener {
		public void onItemChange(int currentPosition);
	}

	public static interface OnItemClickListener {
		public void onItemClick(int currentPosition);
	}
};