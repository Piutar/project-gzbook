"# gzbook-backend" 

PJ Name: GZBook

Description: A social networking web app.

Technology:

- Spring MVC

- OAuth2, Token

- MySQL

- IDE: IntelliJ IDEA



Tính năng Post
Thuộc tính Status của post có giá trị:
0 là private
1 là friend
2 là public 
Lấy list toàn bộ list post:
method: Get
url: http://localhost:8080/post/
Tạo post mới
method: Post
url: http://localhost:8080/post/create
Test:
{
    "userId": 1,
    "textPost": "sas00000000000000000agbdsg.j",
    "imageUrl": "1dsfsfsfsfsfsf",
    "videoUrl": "1",
    "linkPost": "1",
    "createdTime": null,
    "status": 1,
    "postLike": 1,
    "postDislike": 1
}
 
Trả về:
{
    "id": 5,
    "userId": 1,
    "textPost": "sas00000000000000000agbdsg.j",
    "imageUrl": "1dsfsfsfsfsfsf",
    "videoUrl": "1",
    "linkPost": "1",
    "createdTime": "2020.10.12 15:28:09",
    "status": 1,
    "postLike": 1,
    "postDislike": 1
}

Lấy post theo id:
method: Get
url: http://localhost:8080/post/findById/{id}
Test:
url: http://localhost:8080/post/findById/1
{
    "id": 1,
    "userId": 1,
    "textPost": "safaldfbgdsgdsgdsgdsgdgdagbdsg.j",
    "imageUrl": "100sgdshsfhsfhfdjfdhdshgda000000000000000",
    "videoUrl": "111111111111111111",
    "linkPost": "fdfdfdsfdsgsdgfdagdgdsgdg1",
    "createdTime": "2020-10-09 09:21:12",
    "status": 1,
    "postLike": 1,
    "postDislike": 1
}

Tính năng cập nhật post:
method: Put
url: http://localhost:8080/post/update
Test:
url: http://localhost:8080/post/update
{
    "id": 1,
    "userId": 1,
    "textPost": "Test.j",
    "imageUrl": "Test",
    "videoUrl": "00000000000000",
    "linkPost": "11111111111111111",
    "createdTime": "2020-10-09 09:21:12",
    "status": 1,
    "postLike": 1,
    "postDislike": 1
}

- kết quả:
Status: 200 OK

Lấy list post theo user id:
method: Get
url: http://localhost:8080/post/user/{userId}
Test:
url: http://localhost:8080/post/user/1
kết quả:
[
    {
        "id": 1,
        "userId": 1,
        "textPost": "Test.j",
        "imageUrl": "Test",
        "videoUrl": "00000000000000",
        "linkPost": "11111111111111111",
        "createdTime": "2020-10-09 09:21:12",
        "status": 1,
        "postLike": 1,
        "postDislike": 1
    },
    {
        "id": 2,
        "userId": 1,
        "textPost": "safaldfbdagbdsg.j",
        "imageUrl": "1",
        "videoUrl": "1",
        "linkPost": "1",
        "createdTime": "2020-10-09 09:26:00",
        "status": 1,
        "postLike": 1,
        "postDislike": 1
    },
    {
        "id": 3,
        "userId": 1,
        "textPost": "sasfsgdjghfguydtghfjgf234242424242224242aldfbdagbdsg.j",
        "imageUrl": "1",
        "videoUrl": "1",
        "linkPost": "1",
        "createdTime": "2020-10-09 09:27:00",
        "status": 0,
        "postLike": 1,
        "postDislike": 1
    },
    {
        "id": 4,
        "userId": 1,
        "textPost": "sasfsgdjghfguydtghfjgf234242424242224242aldfbdagbdsg.j",
        "imageUrl": "1",
        "videoUrl": "1",
        "linkPost": "1",
        "createdTime": null,
        "status": 1,
        "postLike": 1,
        "postDislike": 1
    },
    {
        "id": 5,
        "userId": 1,
        "textPost": "sas00000000000000000agbdsg.j",
        "imageUrl": "1dsfsfsfsfsfsf",
        "videoUrl": "1",
        "linkPost": "1",
        "createdTime": "2020-10-12 15:28:09",
        "status": 0,
        "postLike": 1,
        "postDislike": 1
    }
]


Lấy post theo user và status:
url: http://localhost:8080/post/userAndStatus/{userId}/{status}


method: Post
Test: http://localhost:8080/post/userAndStatus/1/2

Kết quả:
[
    {
        "id": 3,
        "userId": 1,
        "textPost": "sasfsgdjghfguydtghfjgf234242424242224242aldfbdagbdsg.j",
        "imageUrl": "1",
        "videoUrl": "1",
        "linkPost": "1",
        "createdTime": "2020-10-09 09:27:00",
        "status": 2,
        "postLike": 1,
        "postDislike": 1
    },
    {
        "id": 5,
        "userId": 1,
        "textPost": "sas00000000000000000agbdsg.j",
        "imageUrl": "1dsfsfsfsfsfsf",
        "videoUrl": "1",
        "linkPost": "1",
        "createdTime": "2020-10-12 15:28:09",
        "status": 2,
        "postLike": 1,
        "postDislike": 1
    }
]

Lấy post theo status:
Url: /status/{status}
method: Post
Test: Url: http://localhost:8080/post/status/1
Kết quả: [
    {
        "id": 3,
        "userId": 1,
        "textPost": "sasfsgdjghfguydtghfjgf234242424242224242aldfbdagbdsg.j",
        "imageUrl": "1",
        "videoUrl": "1",
        "linkPost": "1",
        "createdTime": "2020-10-09 09:27:00",
        "status": 2,
        "postLike": 1,
        "postDislike": 1
    },
    {
        "id": 5,
        "userId": 1,
        "textPost": "sas00000000000000000agbdsg.j",
        "imageUrl": "1dsfsfsfsfsfsf",
        "videoUrl": "1",
        "linkPost": "1",
        "createdTime": "2020-10-12 15:28:09",
        "status": 2,
        "postLike": 1,
        "postDislike": 1
    }
]


Lấy theo status(1 lấy đc public và only- friend; 2 lấy đc public)
method: post
Url: /statusIn/{status}
Test- Url:http://localhost:8080/post/status/1

 Kết quả:
{
        "id": 3,
        "userId": 1,
        "textPost": "sasfsgdjghfguydtghfjgf234242424242224242aldfbdagbdsg.j",
        "imageUrl": "1",
        "videoUrl": "1",
        "linkPost": "1",
        "createdTime": "2020-10-09 09:27:00",
        "status": 2,
        "postLike": 1,
        "postDislike": 1
    },
    {
        "id": 4,
        "userId": 1,
        "textPost": "sasfsgdjghfguydtghfjgf234242424242224242aldfbdagbdsg.j",
        "imageUrl": "1",
        "videoUrl": "1",
        "linkPost": "1",
        "createdTime": null,
        "status": 1,
        "postLike": 1,
        "postDislike": 1
    }

************************************************************************
Comment:
Tạo comment: 
Post: http://localhost:8080/comment/create
Test: url như trên
{
    "userId": 1,
    "postId": 1,
    "content": "000000000afafaaffaafafaf00000000000",
    "commentTime": null,
    "edited": 0
}
Kết quả trả về:
{
    "id": 2,
    "userId": 1,
    "postId": 1,
    "content": "000000000afafaaffaafafaf00000000000",
    "commentTime": "13.10.2020 08:53",
    "edited": 0
}

sửa comment: Put:http://localhost:8080/comment/update
{
    "id": 2,
    "userId": 1,
    "postId": 1,
    "content": "1111111111111111111111111111111111111",
    "commentTime": "13.10.2020 08:53",
    "edited": 0
}
Kết quả:
{
    "id": 2,
    "userId": 1,
    "postId": 1,
    "content": "1111111111111111111111111111111111111",
    "commentTime": "13.10.2020 08:53",
    "edited": 0
}

Xóa comment :
Url: /comment/delete/{id}
Test: Http.Status.OK
Lấy comment theo id:
url:/comment/findCommentById/{commentId}
method: Get
Tesr-Url:http://localhost:8080/comment/findCommentById/1
Kết quả:
{
    "id": 1,
    "userId": 1,
    "postId": 1,
    "content": "00000000000000000000",
    "commentTime": "13.10.2020 08:52",
    "edited": 0
}

Lấy comment theo post id:
url:/comment/findCommentsByPostId/{postId}
method: Get
Tesr-Url:http://localhost:8080/comment/findCommentsByPostId/1
Kết quả:
 {
     {  "id": 1,
        "userId": 1,
        "postId": 1,
        "content": "00000000000000000000",
        "commentTime": "13.10.2020 08:52",
        "edited": 0
    },
    {
        "id": 3,
        "userId": 1,
        "postId": 1,
        "content": "000000000aâsasafafaaffaafafaf00000000000",
        "commentTime": "13.10.2020 09:24",
        "edited": 0
    }
}

Tìm comment theo user id:
url:/comment/findByUserId/{commenterId}
method: Get
Tesr-Url:http://localhost:8080/comment/findByUserId/1
Kết quả: Trả về commnet dạng Json


Lấy số lượng comment của post theo id của post:
url:/comment/count/{id}
method: Get
Tesr-Url:http://localhost:8080/comment/count/1
Kết quả: Trả về commnet dạng Json
************************************************************************

Like comment:
url:/comment/like
method: Get
Tesr-Url:http://localhost:8080/comment/like
{
    "commentId": 3,
    "userId": 2
}
Kết quả: Trả về 1 nếu tạo thành công, 0 đã like

Lấy danh sách like của 1 comment
url:/comment/likes/{postId}
method: Get
Tesr-Url:http://localhost:8080/comment/likes/1
Kết quả: 
[
    {
        "id": 1,
        "commentId": 1,
        "userId": 1
    },
    {
        "id": 2,
        "commentId": 1,
        "userId": 2
    }
]
************************************************************************

Like post
url:/post/like
method: Get
Tesr-Url:http://localhost:8080/post/like
{
    "postId": 3,
    "userId": 2
}
Kết quả: Trả về 1 nếu tạo thành công, -1 hủy like
Lấy danh sách like của 1 comment
url:/post/likes/{postId}
method: Get
Tesr-Url:http://localhost:8080/comment/likes/1
Kết quả: 
[
    {
        "id": 1,
        "postId": 1,
        "userId": 1
    },
    {
        "id": 5,
        "postId": 1,
        "userId": 3
    }
]
************************************************************************

Lấy danh sách bạn bè chung
Method:Get
Url:/relationship/mutualFriends/{userId}/{friendId}

Test: Url:http://localhost:8080/relationship/mutualFriends/1/3
Kết quả:
[
    {
        "id": 3,
        "relatingUserId": 2,
        "relatedUserId": 3,
        "status": {
            "id": 2,
            "name": "friend",
            "relationships": []
        }
    }
]

data-import:
[
 {
   "id": 1,
   "related_user_id": 1,
   "relating_user_id": 2,
   "status_id": 2
 },
 {
   "id": 2,
   "related_user_id": 1,
   "relating_user_id": 3,
   "status_id": 2
 },
 {
   "id": 3,
   "related_user_id": 3,
   "relating_user_id": 2,
   "status_id": 2
 },
 {
   "id": 4,
   "related_user_id": 3,
   "relating_user_id": 1,
   "status_id": 2
 },
 {
   "id": 5,
   "related_user_id": 2,
   "relating_user_id": 1,
   "status_id": 2
 },
 {
   "id": 6,
   "related_user_id": 2,
   "relating_user_id": 3,
   "status_id": 2
 },
 {
   "id": 7,
   "related_user_id": 1,
   "relating_user_id": 4,
   "status_id": 2
 },
 {
   "id": 8,
   "related_user_id": 1,
   "relating_user_id": 5,
   "status_id": null
 },
 {
   "id": 9,
   "related_user_id": 4,
   "relating_user_id": 1,
   "status_id": 2
 },
 {
   "id": 10,
   "related_user_id": 4,
   "relating_user_id": 5,
   "status_id": null
 },
 {
   "id": 11,
   "related_user_id": 2,
   "relating_user_id": 4,
   "status_id": 2
 },
 {
   "id": 12,
   "related_user_id": 3,
   "relating_user_id": 4,
   "status_id": 2
 },
 {
   "id": 13,
   "related_user_id": 2,
   "relating_user_id": 5,
   "status_id": 2
 },
 {
   "id": 14,
   "related_user_id": 3,
   "relating_user_id": 5,
   "status_id": 2
 }
]

************************************************************************

Tìm post theo user-Id và nội dung nhập vào
Method: Get
Url:/post/search/{userId}
Body chứ chỗi String nd cần tìm
Test
Url:http://localhost:8080/post/search/1
Body:
1111

Kết quả:
[
    {
        "id": 1,
        "userId": 1,
        "textPost": "Test.j1111111111111111111111111111",
        "imageUrl": "Test",
        "videoUrl": "00000000000000",
        "linkPost": "11111111111111111",
        "createdTime": "2020-10-09 09:21:12",
        "status": 1,
        "postLike": 1,
        "postDislike": 1
    },
    {
        "id": 2,
        "userId": 1,
        "textPost": "safaldfbdagbdsg.1111111111111111111",
        "imageUrl": "1",
        "videoUrl": "1",
        "linkPost": "1",
        "createdTime": "2020-10-09 09:26:00",
        "status": 1,
        "postLike": 1,
        "postDislike": 1
    }
]

************************************************************************
Đông
DATABASE
Roles
1 : ROLE_USER
2 : ROLE_ADMIN

// Register/Login/Logout
Đăng ký - POST : http://localhost:8080/api/auth/register
{
    "username":"adminadmin",
    "gender":"Male",
    "email":"admin1admin@gmail.com",
    "password":"123456"
}


Đăng nhập - POST : http://localhost:8080/api/auth/login
{
 	"email":"admin@gmail.com",
    	"password":"123456"
}

Đăng xuất - POST : http://localhost:8080/api/auth/logout

// User 
Lấy danh sách - thông tin cá nhân users - GET
http://localhost:8080/user/

Update thông tin cá nhân user - PUT
http://localhost:8080/user/update/10
{
    "id": 10,
    "username": "Đào Xuân Đông",
    "email": "daoxuandong@gmail.com",
    "password": "123456",
    "gender": "Male",
    "dateOfBirth": "02/12/1997",
    "address": "Hà Nội",
    "phoneNumber": "0964224490",
    "avatarUrl": "đéo có",
    "coverPhotoUrl": "cũng đéo có",
    "about": "FuckThugLife",
    "roles": []
}
Xóa 1 user - DELETE
http://localhost:8080/user/delete/{id}
Example: 
http://localhost:8080/user/delete/10
Tìm 1 user bằng id - GET
http://localhost:8080/user/findUserById/{id}
Example: 
http://localhost:8080/user/findUserById/10
{
    "id": 10,
    "username": "Đào Xuân Đông",
    "email": "daoxuandong@gmail.com",
    "password": "123456",
    "gender": "Male",
    "dateOfBirth": "02/12/1997",
    "address": "Hà Nội",
    "phoneNumber": "0964224490",
    "avatarUrl": "đéo có",
    "coverPhotoUrl": "cũng đéo có",
    "about": "FuckThugLife",
    "roles": []
}
Lấy thông tin 1 user bằng email - GET
http://localhost:8080/user/findUserByEmail?email={email}
Example:
http://localhost:8080/user/findUserByEmail?email=daoxuandong@gmail.com
{
    "id": 10,
    "username": "Đào Xuân Đông",
    "email": "daoxuandong@gmail.com",
    "password": "123456",
    "gender": "Male",
    "dateOfBirth": "02/12/1997",
    "address": "Hà Nội",
    "phoneNumber": "0964224490",
    "avatarUrl": "đéo có",
    "coverPhotoUrl": "cũng đéo có",
    "about": "FuckThugLife",
    "roles": []
}
Lấy thông tin 1 user bằng tên(username) - GET
http://localhost:8080/user/findUserByName?name={name}
Example: 
http://localhost:8080/user/findUserByName?name=Đông
[
    {
        "id": 10,
        "username": "Đào Xuân Đông",
        "email": "daoxuandong@gmail.com",
        "password": "123456",
        "gender": "Male",
        "dateOfBirth": "02/12/1997",
        "address": "Hà Nội",
        "phoneNumber": "0964224490",
        "avatarUrl": "đéo có",
        "coverPhotoUrl": "cũng đéo có",
        "about": "FuckThugLife",
        "roles": []
    }
]
 
 
// FRIEND

Statuses
1 : PENDING
2 : FRIEND

RelatingUser : Tài khoản gửi lời mời kết bạn
RelatedUser : Tài khoản nhận lời mời kết bạn 



Tạo yêu cầu kết bạn từ relatingUser đến relatedUser 
Method - POST
http://localhost:8080/relationship/create/{relatingId}
Example : 
( Gửi yêu cầu kết bạn từ 1 tài khoản có id: 1 
đến 1 tài khoản có id: 2)

http://localhost:8080/relationship/create/1
 {
    "id" : "2"
 }
Đồng ý kết bạn từ relatedUser
Method - PUT
http://localhost:8080/relationship/edit/{relatedId}/{statusId}
Example :
(Đăng nhập vào tài khoản có id: 2 và chấp nhận lời mời kết bạn)
 
http://localhost:8080/relationship/edit/2/2 
 {
    "id" : "1"
 }
Xóa lời mời kết bạn - xóa trạng thái là bạn bè(hủy kết bạn)
Method : DELETE
http://localhost:8080/relationship/delete/{relationshipId}
Example : 
http://localhost:8080/relationship/delete/1
Lấy danh sách bạn bè
Method : GET
http://localhost:8080/relationship/listFriend/{userId}
Example :
http://localhost:8080/relationship/listFriend/3
Kết quả
 
[
    {
        "id": 4,
        "username": "aMinh",
        "email": "aminh@gmail.com",
        "password": "$2a$10$nT33TkZL2WnehRYuB.yPleQClaTSTBBNToh.Vrf06uHkfVNjIVGdi",
        "gender": "Male",
        "dateOfBirth": "--",
        "address": null,
        "phoneNumber": null,
        "avatarUrl": "https://firebasestorage.googleapis.com/v0/b/porject-module6-vh2.appspot.com/o/wp3313545%20-%20Copy.jpg?alt=media&token=f870ab79-247d-4b39-88c6-454fb39557f1",
        "coverPhotoUrl": "https://firebasestorage.googleapis.com/v0/b/porject-module6-vh2.appspot.com/o/wp3313545.jpg?alt=media&token=353b7206-321a-4eee-b845-36015a19c37e",
        "about": null,
        "roles": [
            {
                "id": 1,
                "name": "ROLE_USER"
            }
        ]
    },
    {
        "id": 1,
        "username": "dong",
        "email": "dong@gmail.com",
        "password": "$2a$10$ippeEDAvN3Z/hZTGx.a4wO3jvhfO6AJ2iQHQQnzKR6/x0Q1UuJ9iC",
        "gender": "Male",
        "dateOfBirth": "--",
        "address": null,
        "phoneNumber": null,
        "avatarUrl": "https://firebasestorage.googleapis.com/v0/b/porject-module6-vh2.appspot.com/o/wp3313545%20-%20Copy.jpg?alt=media&token=f870ab79-247d-4b39-88c6-454fb39557f1",
        "coverPhotoUrl": "https://firebasestorage.googleapis.com/v0/b/porject-module6-vh2.appspot.com/o/wp3313545.jpg?alt=media&token=353b7206-321a-4eee-b845-36015a19c37e",
        "about": null,
        "roles": []
    },
    {
        "id": 5,
        "username": "hieu",
        "email": "hieu@gmail.com",
        "password": "$2a$10$C3m5NiyVwZnypKfwCChRJ.jJ2PyCB1Q.XwXrS4awnI67vSvjDIRYK",
        "gender": "Male",
        "dateOfBirth": "--",
        "address": null,
        "phoneNumber": null,
        "avatarUrl": "https://firebasestorage.googleapis.com/v0/b/porject-module6-vh2.appspot.com/o/wp3313545%20-%20Copy.jpg?alt=media&token=f870ab79-247d-4b39-88c6-454fb39557f1",
        "coverPhotoUrl": "https://firebasestorage.googleapis.com/v0/b/porject-module6-vh2.appspot.com/o/wp3313545.jpg?alt=media&token=353b7206-321a-4eee-b845-36015a19c37e",
        "about": null,
        "roles": [
            {
                "id": 1,
                "name": "ROLE_USER"
            }
        ]
    }
]
Danh sách pending từ tài khoản relatedUser
Method : GET
http://localhost:8080/relationship/listPending/{relatedUserId}
Example : User có id là 2 đang được nhận lời mời từ 2 user khác
http://localhost:8080/relationship/listPending/2
Kết quả
[
    {
        "id": 3,
        "username": "hung",
        "email": "hung@gmail.com",
        "password": "$2a$10$sXaNA/EA6gyy7.AkVFPx/uEHOu09Q0svbVHB3RkYLBrgMmCp7mWKW",
        "gender": "Male",
        "dateOfBirth": "--",
        "address": null,
        "phoneNumber": null,
        "avatarUrl": "https://firebasestorage.googleapis.com/v0/b/porject-module6-vh2.appspot.com/o/wp3313545%20-%20Copy.jpg?alt=media&token=f870ab79-247d-4b39-88c6-454fb39557f1",
        "coverPhotoUrl": "https://firebasestorage.googleapis.com/v0/b/porject-module6-vh2.appspot.com/o/wp3313545.jpg?alt=media&token=353b7206-321a-4eee-b845-36015a19c37e",
        "about": null,
        "roles": [
            {
                "id": 1,
                "name": "ROLE_USER"
            }
        ]
    },
    {
        "id": 4,
        "username": "aMinh",
        "email": "aminh@gmail.com",
        "password": "$2a$10$nT33TkZL2WnehRYuB.yPleQClaTSTBBNToh.Vrf06uHkfVNjIVGdi",
        "gender": "Male",
        "dateOfBirth": "--",
        "address": null,
        "phoneNumber": null,
        "avatarUrl": "https://firebasestorage.googleapis.com/v0/b/porject-module6-vh2.appspot.com/o/wp3313545%20-%20Copy.jpg?alt=media&token=f870ab79-247d-4b39-88c6-454fb39557f1",
        "coverPhotoUrl": "https://firebasestorage.googleapis.com/v0/b/porject-module6-vh2.appspot.com/o/wp3313545.jpg?alt=media&token=353b7206-321a-4eee-b845-36015a19c37e",
        "about": null,
        "roles": [
            {
                "id": 1,
                "name": "ROLE_USER"
            }
        ]
    }
]
Kiểm tra trạng thái mối quan hệ
Method : GET
http://localhost:8080/relationship/checkFriend/{relatingId}/{relatedId}
Example : 
http://localhost:8080/relationship/checkFriend/3/4
Kết quả : 
status_id : 2
http://localhost:8080/relationship/checkFriend/3/2
status_id : 1
// hứng link ảnh từ web về database
Avatar cá nhân
http://localhost:8080/photo/avatar/{userId}
	Method : POST
Cover photo
http://localhost:8080/photo/coverPhoto/{userId}
	Method : POST
Post photo
http://localhost:8080/photo/postPhoto/{postId}
	Method : POST



