# Brewery Android App(WIP)

This Android App aims to demonstrate the usage of the popular libraries used in the Android Application development process. It helps to understand the best practices for more maintainable,testable and scalable Android applications

## What does the app do?

It fetches and displays the different Starbucks products from a simple API server that I have set up [here](https://github.com/sdivakarrajesh/Brewery-API-server). The App has a login page that authenticates the user(currently - mock) and then based on the category selected displays the drinks in that category. It also has a favorites sections where the drinks favorited by the users are displayed.

## Libraries Used

Third party libraries:

- [Dagger 2](https://google.github.io/dagger/users-guide) - For Dependency injection
- [ButterKnife](https://jakewharton.github.io/butterknife/) - For view Binding
- [RetroFit](https://square.github.io/retrofit/) - For performing network IO
- [GSON](https://sites.google.com/site/gson/gson-user-guide) - For object mapping of JSON
- [Glide](https://bumptech.github.io/glide/) - For Fetching, caching and displaying images

Android Jetpack:

- [RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview) - For Advanced and Flexible rendering of list items
- [Room](https://developer.android.com/jetpack/androidx/releases/room) - For database

## License

    Copyright 2018 DIVAKAR RAJESH S

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

