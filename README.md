# Marvel Heroes 💪🏻

Marvel Heroes es una app para Android que lista algunos de los súperheroes de Marvel con sus características.

 Main                      |  Detail
:-------------------------:|:-------------------------:
![main](https://raw.githubusercontent.com/costular/marvel-super-heroes/master/art/main.png) | ![detail](https://raw.githubusercontent.com/costular/marvel-super-heroes/master/art/detail.png)

## Características ✨

- Kotlin
- MVVM (uso de ViewModel + LiveData)
- Dagger2
- RxJava2
- Retrofit (DataSource Remoto)
- Room (DataSource Local / Caché de Datos)
- Gestión de 'Favoritos' y persistencia vía SharedPreferences

## TODO LIST 📝

- Se sigue utilizando ActionBar en lugar de Toolbar.

- Se sigue utilizando `notifyDataSetChanged()`. Sin embargo, lo recomendado sería utilizar [DiffUtil](https://developer.android.com/reference/android/support/v7/util/DiffUtil.html), el cual nos calcula la diferencia entre dos listas para refrescar la información con menos consumo de recursos y animando el resultado.

- Podría y debería haber más tests