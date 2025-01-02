@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): BudgetDatabase {
        return Room.databaseBuilder(context, BudgetDatabase::class.java, "budget_db").build()
    }

    @Provides
    fun provideBudgetDao(database: BudgetDatabase): BudgetDao {
        return database.budgetDao()
    }

    @Provides
    fun provideBudgetRepository(dao: BudgetDao): BudgetRepository {
        return BudgetRepositoryImpl(dao)
    }
}
