package com.drops.demo.servlet.pages;

import com.drops.demo.model.ConnectedPage;

public class Config extends ConnectedPage {
	private static final long serialVersionUID = -6112387383930755480L;

	@Override
	protected String getTargetPage() {
		return PAGES_ROOT + "config.jsp";
	}
}
