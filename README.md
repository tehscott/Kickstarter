# Kickstarter

In order to run you just need to check out the source, open the project in Android Studio and run on a device with a minimum API level of 24.

I decided to make this app using the MVVM architecture. It uses a single Activity which contains a single Fragment that uses a ViewModel. I chose a simple list display for the images, using a RecyclerView. The images are fetched asyncronously using Glide. Clicking on an image will launch a web browser and navigate to the Giphy page related to that image.

Given more time, I would do the following to improve the app:

- Rather than allowing the gifs to play as soon as they are loaded, I would have them start paused and only play when in full view on the screen.
- Implement UI tests.
- Rather than opening the gif in a separate web browser, I would have it open in a full screen dialog in the app (to keep the user inside the app).
- I might switch from using a RecyclerView to a GridView/Layout to show the list of images in a prettier way.
- Infinite scrolling (currently it only loads 25 images).
- Pull to refresh.
- Build the layouts using Compose.
- Make sure the placeholder image matches the size of the incoming gif.
