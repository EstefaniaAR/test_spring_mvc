package com.example.test_thymeleaf.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender <T>
{
	private String url;
	private Page<T>page;
	
	private int pageTotal;
	private int elementNumber;
	private int currentPage;
	private List<PageItem>pages;
	private boolean first;
	private boolean last;
	private boolean previous;
	private boolean next;
	
	public PageRender(String uRL, Page<T> page) {
		url = uRL;
		this.page = page;
		this.pages = new ArrayList<PageItem>();
		elementNumber = page.getSize();
		pageTotal = page.getTotalPages();
		currentPage = page.getNumber();
		
		int init=0;
		int end=0;
		if(pageTotal <= elementNumber)
		{
			init =0;
			end = pageTotal;
		}
		else
		{
			if(currentPage <= elementNumber/2)
			{
				init =1;
				end = elementNumber;
			}
			else if(currentPage >= pageTotal - elementNumber)
			{
				init = pageTotal - elementNumber + 1;
				end = pageTotal;
				
			}
			else
			{
				init = currentPage - elementNumber /2;
				end = elementNumber;
						
			}
		}
		
		for (int i =0; i < end ; i++)
		{
			pages.add(new PageItem(init + i, currentPage == init+i));
		}
		
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public Page<T> getPage() {
		return page;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public int getElementNumber() {
		return elementNumber;
	}

	public void setElementNumber(int elementNumber) {
		this.elementNumber = elementNumber;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<PageItem> getPages() {
		return pages;
	}

	public void setPages(List<PageItem> pages) {
		this.pages = pages;
	}
	
	public boolean isFirst()
	{
		first=page.isFirst();
		return first;
	}
	public boolean isLast()
	{
		last=page.isLast();
		return last;
	}
	public boolean isNext()
	{
		next=page.hasNext();
		return next;
	}
	public boolean isPrevious()
	{
		previous=page.hasPrevious();
		return previous;
	}
	

}
