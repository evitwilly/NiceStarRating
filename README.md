# NiceStarRatingView
A simple view to display the rating with stars

[preview](https://github.com/evitwilly/NiceStarRating/assets/40917658/f7da01e5-c415-4bf6-8ffd-0c5d743ee563)

## NiceStarRatingView options

`rating` - current rating value

`maxRating` - max rating value

`starWidth` - star width in dp, default the star is stretched because layout_weight=1

    <ru.freeit.lib.NiceStarRatingView
        android:id="@+id/nice_rating_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:rating="4"
        app:maxRating="5"
        app:starWidth="48dp" />        

Output:

<img alt="screen1" src="https://github.com/evitwilly/NiceStarRating/assets/40917658/ee4d9dee-a030-4ce6-ab7f-772ac7bed19e" width="480px" />

___

`horizontalMargin` - margin between two neighboring stars 

`color` - color for drawing stars

    <ru.freeit.lib.NiceStarRatingView
        android:id="@+id/nice_rating_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:rating="4"
        app:maxRating="5"
        app:starWidth="48dp"
        app:horizontalMargin="16dp"
        app:color="?colorPrimary" />

Output:

<img alt="screen2" src="https://github.com/evitwilly/NiceStarRating/assets/40917658/dba8a4fd-232a-42c0-b9a2-55fe0fbc29fe" width="480px" />

___

`armNumber` - number of vertices on a star

`strokeWidth` - line thickness when the star is not filled

    <ru.freeit.lib.NiceStarRatingView
        android:id="@+id/nice_rating_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:rating="2"
        app:maxRating="4"
        app:starWidth="48dp"
        app:horizontalMargin="12dp"
        app:color="?colorSecondary"
        app:armNumber="6"
        app:strokeWidth="3dp" />

Output:

<img alt="screen3" src="https://github.com/evitwilly/NiceStarRating/assets/40917658/5eea0524-49c9-4dc4-b65d-0dab5edd4369" width="480px" />

___

`halfOpportunity` - the ability to put half the rating

    <ru.freeit.lib.NiceStarRatingView
        android:id="@+id/nice_rating_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:rating="3.5"
        app:starWidth="48dp"
        app:halfOpportunity="true" />

Output:

<img alt="screen4" src="https://github.com/evitwilly/NiceStarRating/assets/40917658/fdf064a5-8342-4023-a29e-cd6680eebde4" width="480px" />

___

`isAnimatingEnabled` - animation enabled or not

`starAnimationDuration` - animation duration for one star

    <ru.freeit.lib.NiceStarRatingView
        android:id="@+id/nice_rating_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:rating="3"
        app:starWidth="48dp"
        app:isAnimatingEnabled="true"
        app:starAnimationDuration="60" />

Output:

[scaling_animation.webm](https://github.com/evitwilly/NiceStarRating/assets/40917658/6a649909-99b9-4944-817a-e18786b685a0)

Custom animator:

    // when we set a higher rating value than the previous one
    niceStarRatingView.animationRatingIncrease = { view ->
        val animator = ValueAnimator.ofFloat(0f, 45f, 0f)
        animator.addUpdateListener {
            view.rotation = it.animatedValue as Float
        }
        animator
    }
    
    // when we set a lower rating value than the previous one
    niceStarRatingView.animationRatingDecrease = { view ->
        val animator = ValueAnimator.ofFloat(0f, -45f, 0f)
        animator.addUpdateListener {
            view.rotation = it.animatedValue as Float
        }
        animator
    }

Output:

[rotation_animation.webm](https://github.com/evitwilly/NiceStarRating/assets/40917658/c72f8455-bca0-4ba3-b623-bba0c82bde66)

# Demo app

You can clone the repository and open it in Android Studio then run:

![screen4](https://github.com/evitwilly/NiceStarRatingView/assets/40917658/fd66cc7a-40fb-44a5-b614-c3ea779e3d27)


# Using in your projects

### Maven

Add dependencies:

```xml
<dependency>
    <groupId>io.github.dmitrytsyvtsyn</groupId>
    <artifactId>nicestarratingview</artifactId>
    <version>1.0.3</version>
</dependency>
```

### Gradle

Add dependencies:

```kotlin
dependencies {
    implementation("io.github.dmitrytsyvtsyn:nicestarratingview:1.0.3")
}
```

And make sure that you have mavenCentral() in the list of repositories:

```kotlin
repositories {
    mavenCentral()
}
```

Enjoy!

# License

    MIT License
    
    Copyright (c) 2023 Dmitry Tsyvtsyn
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
