package finance.domain.transactions;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CategoryTransactions {
    // 🏠 Housing & Utilities
    HOUSING,
    UTILITIES,
    RENT_MORTGAGE,

    // 🚗 Transportation
    TRANSPORTATION,
    GAS_FUEL,
    CAR_MAINTENANCE,
    PUBLIC_TRANSPORT,

    // 🍽️ Food & Dining
    GROCERIES,
    RESTAURANTS,
    DELIVERY,
    COFFEE_SNACKS,

    // 💳 Financial
    LOAN_PAYMENTS,
    CREDIT_CARD_PAYMENTS,
    BANK_FEES,
    INVESTMENTS,
    INSURANCE,

    // 💼 Work & Business
    OFFICE_SUPPLIES,
    SOFTWARE_SUBSCRIPTIONS,
    PROFESSIONAL_DEVELOPMENT,
    BUSINESS_EXPENSES,

    // 🛍️ Shopping & Personal
    CLOTHING,
    PERSONAL_CARE,
    HOUSEHOLD_ITEMS,
    GIFTS,

    // 🏥 Healthcare
    HEALTH_INSURANCE,
    MEDICAL_EXPENSES,
    PHARMACY,
    FITNESS_GYM,

    // 🎉 Entertainment & Lifestyle
    MOVIES_ENTERTAINMENT,
    CONCERTS_EVENTS,
    TRAVEL_VACATION,
    STREAMING,
    HOBBIES,

    // 🎓 Education
    TUITION_FEES,
    ONLINE_COURSES,
    BOOKS_SUPPLIES,
    STUDENT_LOANS,

    // 💰 Income Categories
    SALARY,
    FREELANCE,
    INVESTMENT_INCOME,
    RENTAL_INCOME,
    BUSINESS_INCOME,
    TAX_REFUND,
    BONUS,

    // 🐾 Pets
    PET_FOOD,
    VETERINARY,
    PET_SUPPLIES,

    // 🎁 Charity & Gifts
    DONATIONS,
    CHARITY,

    // 📱 Technology
    PHONE_BILL,
    INTERNET,
    ELECTRONICS,

    // 🏦 Taxes & Government
    TAXES,
    GOVERNMENT_FEES,

    // 🔧 Miscellaneous
    OTHER_EXPENSES,
    OTHER_INCOME;

    @JsonCreator
    public static CategoryTransactions fromString(String value) {
        return CategoryTransactions.valueOf(value.toUpperCase());
    }

}
