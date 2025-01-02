class GetBudgetUseCase(private val budgetRepository: BudgetRepository) {
    suspend operator fun invoke(): List<Budget> {
        return budgetRepository.getBudgets()
    }
}
