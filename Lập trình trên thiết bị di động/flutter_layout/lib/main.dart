import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Layout',
      theme: ThemeData(
        primarySwatch: Colors.lightBlue,
      ),
      home: HomePage(),
    );
  }
}

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Flutter Layout'),
      ),
      body: SingleChildScrollView(
        padding: EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            ElevatedButton(
              onPressed: () {
                // TODO: Implement search functionality
              },
              style: ButtonStyle(
                backgroundColor: MaterialStateProperty.resolveWith<Color?>(
                      (Set<MaterialState> states) {
                    if (states.contains(MaterialState.pressed)) {
                      return Colors.grey;
                    }
                    return Colors.white54; // Use the component's default.
                  },
                ),
              ),
              child: Text('Search'),
            ),
            SizedBox(height: 16.0),
            Row(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                ReelItem(),
                SizedBox(width: 8.0),
                ReelItem(),
                SizedBox(width: 8.0),
                ReelItem(),
              ],
            ),
            SizedBox(height: 16.0),
            PostItem(
              avatarUrl: 'https://w7.pngwing.com/pngs/215/58/png-transparent-computer-icons-google-account-scalable-graphics-computer-file-my-account-icon-rim-123rf-symbol-thumbnail.png',
              name: 'Nhi Le',
              description:
                  'Tôi nghĩ thời tiết hôm nay không được đẹp lắm',
              comments: [
                Comment(
                  avatarUrl: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSL4jg6HagwuqvuB5iOYqOMs8eUxXZxN7Pjp0mYU_Rksp41FNbYya71GfpWNaCaS-9Ksug&usqp=CAU',
                  name: 'Nhi Huynh',
                  comment: 'Đúng vậy',
                ),
                Comment(
                  avatarUrl: 'https://cdn0.iconfinder.com/data/icons/thin-users-people/57/thin-192_user_profile_avatar_female-512.png',
                  name: 'Mi Nguyen',
                  comment: 'Tôi cũng nghĩ như vậy',
                ),
              ],
              onPressedViewComments: () {
                // TODO: Implement view comments functionality
              },
            ),
            SizedBox(height: 8.0),
            PostItem(
              avatarUrl: 'https://cdn0.iconfinder.com/data/icons/thin-users-people/57/thin-192_user_profile_avatar_female-512.png',
              name: 'Mi Nguyen',
              description:
                  'Hôm nay tôi đã đậu phỏng vấn',
              comments: [
                Comment(
                  avatarUrl: 'https://w7.pngwing.com/pngs/215/58/png-transparent-computer-icons-google-account-scalable-graphics-computer-file-my-account-icon-rim-123rf-symbol-thumbnail.png',
                  name: 'Nhi Le',
                  comment: 'Chúc mừng bạn nha!',
                ),
              ],
              onPressedViewComments: () {
                // TODO: Implement view comments functionality
              },
            ),
          ],
        ),
      ),
    );
  }
}

class ReelItem extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      width: 100.0,
      height: 100.0,
      color: Colors.grey,
      // TODO: Add reel content (e.g., image, play button)
    );
  }
}

class PostItem extends StatelessWidget {
  final String avatarUrl;
  final String name;
  final String description;
  final List<Comment> comments;
  final VoidCallback onPressedViewComments;

  const PostItem({
    required this.avatarUrl,
    required this.name,
    required this.description,
    required this.comments,
    required this.onPressedViewComments,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(16.0),
      decoration: BoxDecoration(
        border: Border.all(color: Colors.grey),
        borderRadius: BorderRadius.circular(8.0),
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            children: [
              CircleAvatar(
                radius: 20.0,
                backgroundImage: NetworkImage(avatarUrl),
              ),
              SizedBox(width: 8.0),
              Text(
                name,
                style: TextStyle(
                  fontSize: 16.0,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ],
          ),
          SizedBox(height: 8.0),
          Text(
            description,
            style: TextStyle(
              fontSize: 14.0,
            ),
          ),
          SizedBox(height: 8.0),


            ElevatedButton(
              onPressed: onPressedViewComments,
              child: Text('View Comments'),
              style: ButtonStyle(
              backgroundColor: MaterialStateProperty.resolveWith<Color?>(
                    (Set<MaterialState> states) {
                  if (states.contains(MaterialState.pressed)) {
                    return Colors.grey;
                  }
                  return Colors.white54; // Use the component's default.
                },
              ),
            ),
            ),
          SizedBox(height: 8.0),
          ListView.builder(
            shrinkWrap: true,
            physics: NeverScrollableScrollPhysics(),
            itemCount: comments.length,
            itemBuilder: (context, index) {
              final comment = comments[index];
              return CommentItem(
                avatarUrl: comment.avatarUrl,
                name: comment.name,
                comment: comment.comment,
              );
            },
          ),
        ],
      ),
    );
  }
}

class Comment {
  final String avatarUrl;
  final String name;
  final String comment;

  const Comment({
    required this.avatarUrl,
    required this.name,
    required this.comment,
  });
}

class CommentItem extends StatelessWidget {
  final String avatarUrl;
  final String name;
  final String comment;

  const CommentItem({
    required this.avatarUrl,
    required this.name,
    required this.comment,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.symmetric(vertical: 4.0),
      child: Row(
        children: [
          CircleAvatar(
            radius: 16.0,
            backgroundImage: NetworkImage(avatarUrl),
          ),
          SizedBox(width: 8.0),
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                name,
                style: TextStyle(
                  fontSize: 14.0,
                  fontWeight: FontWeight.bold,
                ),
              ),
              SizedBox(height: 4.0),
              Text(
                comment,
                style: TextStyle(fontSize: 12.0),
              ),
            ],
          ),
        ],
      ),
    );
  }
}