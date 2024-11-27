package tests.vendors.model;

public record VendorTestData(String username, 
		String password,
		String monthlyEarning,
		String annualEarning,
		String profitMargin,
		int availableInventory,
		String searchKeyword,
		int searchResultsCount) {
}
