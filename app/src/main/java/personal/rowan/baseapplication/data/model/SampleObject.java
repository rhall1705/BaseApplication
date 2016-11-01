package personal.rowan.baseapplication.data.model;

import java.util.UUID;

public class SampleObject {

	private String mId;
	private String mTitle;
	private String mDetail;

	public SampleObject(String title, String detail) {
		mId = UUID.randomUUID().toString();
		mTitle = title;
		mDetail = detail;
	}

	public SampleObject(String id, String title, String detail) {
		mId = id;
		mTitle = title;
		mDetail = detail;
	}

	public String getId() {
		return mId;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public String getDetail() {
		return mDetail;
	}

	public void setDetail(String detail) {
		mDetail = detail;
	}

}
