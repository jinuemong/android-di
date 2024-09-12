package woowacourse.shopping.data.di

import android.content.Context
import com.android.di.component.DiSingletonComponent
import woowacourse.shopping.data.CartProductDao
import woowacourse.shopping.data.ImMemoryShoppingDatabase
import woowacourse.shopping.data.ShoppingDatabase
import woowacourse.shopping.data.createInMemoryDatabase
import woowacourse.shopping.data.createRoomDatabase

object LocalModule {
    fun install(context: Context) {
        provideShoppingDatabase(context)
        provideInMemoryShoppingDatabase(context)
    }

    private fun provideShoppingDatabase(context: Context) {
        val database = createRoomDatabase(context)
        DiSingletonComponent.provide(
            ShoppingDatabase::class,
            database,
        )
        provideRoomCartProductDao(database)
    }

    private fun provideInMemoryShoppingDatabase(context: Context) {
        val database = createInMemoryDatabase(context)
        DiSingletonComponent.provide(
            ImMemoryShoppingDatabase::class,
            database,
        )
        provideInMemoryCartProductDao(database)
    }

    private fun provideRoomCartProductDao(database: ShoppingDatabase) {
        DiSingletonComponent.provide(
            CartProductDao::class,
            database.cartProductDao(),
        )
    }

    private fun provideInMemoryCartProductDao(database: ImMemoryShoppingDatabase) {
        DiSingletonComponent.provide(
            CartProductDao::class,
            database.cartProductDao(),
        )
    }
}
