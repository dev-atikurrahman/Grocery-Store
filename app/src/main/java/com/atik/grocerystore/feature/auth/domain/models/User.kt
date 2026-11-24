package com.atik.grocerystore.feature.auth.domain.models

data class User(
    val id: Int,
    val email: String,
    val role: String = "super_admin" // Roles can be 'super_admin' or 'admin'; a 'super_admin' has access to everything, while an 'admin' has access to specific areas.
)

data class StoreProfile(
    val storeName: String? = null,
    val storeLogoUrl: String? = null,
    val description: String? = null,
    val categories: List<String>? = null,
    val contract: ContractDetails,
)

data class ContractDetails(
    val phoneNumber: String? = null,
    val emailAddress: String? = null,
    val websites: String? = null, // Links to the store's online storefront or facebook/instagram pages
    val address: Address,
    val mapCoordinated: String? = null, // Exact GPS location for find store

)
data class Address(
    val street: String,
    val city: String,
    val zipCode: String
)