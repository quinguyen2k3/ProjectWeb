package paging;

public class PageRequest implements Pageble {
	private Integer page;
	private Integer maxPageItem;
	

	public PageRequest(Integer page, Integer maxPageItem) {
		this.page = page;
		this.maxPageItem = maxPageItem;
	}
	
	public Integer getPage() {
		return this.page;
	}
	
	public Integer getOffset() {
		if (this.page != null && this.maxPageItem != null) {
			return (this.page - 1) * this.maxPageItem;
		}
		return null;
	}

	public Integer getLimit() {
		return this.maxPageItem;
	}
}
