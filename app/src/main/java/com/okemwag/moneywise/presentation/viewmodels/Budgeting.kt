@HiltViewModel
class BudgetViewModel @Inject constructor(
    private val getBudgetUseCase: GetBudgetUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<BudgetUiState>(BudgetUiState.Loading)
    val uiState: StateFlow<BudgetUiState> = _uiState

    fun loadBudget() {
        viewModelScope.launch {
            _uiState.value = try {
                val budget = getBudgetUseCase()
                BudgetUiState.Success(budget)
            } catch (e: Exception) {
                BudgetUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class BudgetUiState {
    object Loading : BudgetUiState()
    data class Success(val budget: Budget) : BudgetUiState()
    data class Error(val message: String) : BudgetUiState()
}
