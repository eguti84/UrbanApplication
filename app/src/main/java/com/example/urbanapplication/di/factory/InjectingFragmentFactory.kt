package com.example.urbanapplication.di.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject
import javax.inject.Provider

class InjectingFragmentFactory @Inject constructor(
    private val creators: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<Fragment>>
) : FragmentFactory() {

    init {
        println("Creating injection factory")
    }

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val fragmentClass = loadFragmentClass(classLoader, className)
        val creator =
            creators[fragmentClass] ?: return createFragmentAsFallback(classLoader, className)

        try {
            return creator.get()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun createFragmentAsFallback(classLoader: ClassLoader, className: String): Fragment {
        println("No creator found for class: $className. Using default constructor")
        return super.instantiate(classLoader, className)
    }
}