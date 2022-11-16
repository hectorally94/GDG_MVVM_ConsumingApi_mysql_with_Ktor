package com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdginjection

import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgDomain.repository.DomainRepository
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgdata.remote.networks.ApiServiceImpl
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgdata.remote.services.ApiService
import com.example.gdg_mvvm_consumingapi_mysql_with_ktor.gdgdata.repository.DomainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    @Provides
    @Singleton
    fun provideApi(client: HttpClient): ApiService = ApiServiceImpl(client)

    @Provides
    fun provideRepository(api: ApiService): DomainRepository = DomainRepositoryImpl(api)
}