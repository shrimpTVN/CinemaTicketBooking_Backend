###INSERT GENRE#######
insert into genre(name, description, created_by)
    value ('Hành động',
           'Phim hành động là thể loại phim tập trung vào những cảnh quay kịch tính, thường có nhiều pha hành động, chiến đấu, và mạo hiểm. Những bộ phim này thường mang đến cho khán giả cảm giác hồi hộp và kích thích.',
           'admin'),
    ('Phiêu lưu',
     'Phim phiêu lưu là thể loại phim tập trung vào những cuộc hành trình, khám phá, và trải nghiệm mới. Những bộ phim này thường có cốt truyện hấp dẫn, với những tình tiết ly kỳ và những cảnh quay đẹp mắt.',
     'admin'),
    ('Hài hước',
     'Phim hài hước là thể loại phim tập trung vào việc tạo ra tiếng cười và giải trí cho khán giả. Những bộ phim này thường có những tình huống hài hước, những nhân vật vui nhộn, và những câu thoại dí dỏm.',
     'admin'),
    ('Kinh dị',
     'Phim kinh dị là thể loại phim tập trung vào việc tạo ra sự sợ hãi và căng thẳng cho khán giả. Những bộ phim này thường có những cảnh quay đáng sợ, những tình tiết ly kỳ, và những nhân vật đáng sợ.',
     'admin'),
    ('Lãng mạng',
     'Phim lãng mạng là thể loại phim tập trung vào việc kể chuyện về những mối quan hệ tình cảm, tình yêu và các vấn đề liên quan đến đời sống hôn nhân, gia đình.',
     'admin'),
    ('Khoa học viễn tưởng',
     'Phim khoa học viễn tưởng là thể loại phim tập trung vào những câu chuyện về công nghệ, không gian, thời gian, và các hiện tượng siêu nhiên. Những bộ phim này thường có những cảnh quay đẹp mắt và những tình tiết ly kỳ.',
     'admin'),
    ('Tâm lý',
     'Phim tâm lý là thể loại phim tập trung vào việc khám phá tâm lý của nhân vật và các mối quan hệ giữa họ. Những bộ phim này thường có những tình tiết phức tạp và những nhân vật sâu sắc.',
     'admin'),
    ('Hoạt hình',
     'Phim hoạt hình là thể loại phim sử dụng kỹ thuật hoạt hình để tạo ra hình ảnh và chuyển động. Những bộ phim này thường có những câu chuyện hấp dẫn và những nhân vật đáng yêu, phù hợp với mọi lứa tuổi.',
     'admin');

###INSERT MOVIE#######
insert into movie(title, duration, avatar, trailer, description, country, premiere_date, actors, director, created_by)
    value ('Avengers: Endgame', 181, 'https://upload.wikimedia.org/wikipedia/en/0/0d/Avengers_Endgame_poster.jpg',
           'https://www.youtube.com/watch?v=TcMBFSGVi1c',
           'Avengers: Endgame là phần tiếp theo của Avengers: Infinity War, kể về cuộc chiến cuối cùng giữa các siêu anh hùng và Thanos để cứu lấy vũ trụ.',
           'Mỹ', '2019-04-26',
           'Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth, Scarlett Johansson, Jeremy Renner, Don Cheadle, Paul Rudd, Brie Larson, Karen Gillan, Danai Gurira, Benedict Wong, Jon Favreau, Bradley Cooper, Josh Brolin',
           'Anthony Russo, Joe Russo', 'admin'),
    ('The Lion King', 118, 'https://upload.wikimedia.org/wikipedia/en/3/3d/The_Lion_King_poster.jpg',
     'https://www.youtube.com/watch?v=7TavVZMewpY',
     'The Lion King là một bộ phim hoạt hình kể về cuộc phiêu lưu của Simba, một chú sư tử con trên hành trình trở thành vua của vùng đất Pride Rock.',
     'Mỹ', '2019-07-19',
     'Donald Glover, Beyoncé Knowles-Carter, Seth Rogen, Chiwetel Ejiofor, Alfre Woodard, Billy Eichner', 'Jon Favreau',
     'admin'),
    ('Joker', 122, 'https://upload.wikimedia.org/wikipedia/en/e/e1/Joker_%282019_film%29_poster.jpg',
     'https://www.youtube.com/watch?v=zAGVQLHvwOY',
     'Joker là một bộ phim tâm lý kể về quá trình biến đổi của Arthur Fleck từ một người đàn ông bình thường thành kẻ phản diện nổi tiếng Joker.',
     'Mỹ', '2019-10-04', 'Joaquin Phoenix, Robert De Niro, Zazie Beetz, Frances Conroy', 'Todd Phillips', 'admin'),
    ('Parasite', 132, 'https://upload.wikimedia.org/wikipedia/en/5/53/Parasite_%282019_film%29.png',
     'https://www.youtube.com/watch?v=5xH0HfJHsaY',
     'Parasite là một bộ phim kinh dị, hài hước và drama kể về gia đình Kim, một gia đình nghèo khổ và gia đình Park, một gia đình giàu có.',
     'Hàn Quốc', '2019-05-30',
     'Song Kang-ho, Lee Sun-kyun, Cho Yeo-jeong, Choi Woo-shik', 'Bong Joon Ho', 'admin'),
    ('Inception', 148, 'https://upload.wikimedia.org/wikipedia/en/7/7f/Inception_ver3.jpg',
     'https://www.youtube.com/watch?v=YoHD9XEInc0',
     'Inception là một bộ phim khoa học viễn tưởng kể về một nhóm chuyên gia xâm nhập vào giấc mơ của người khác để trộm thông tin hoặc cấy ghép ý tưởng.',
     'Mỹ', '2010-07-16', 'Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page, Tom Hardy', 'Christopher Nolan', 'admin'),
    ('The Dark Knight', 152, 'https://upload.wikimedia.org/wikipedia/en/8/8a/Dark_Knight.jpg',
     'https://www.youtube.com/watch?v=EXeTwQWrcwY',
     'The Dark Knight là phần tiếp theo của Batman Begins, kể về cuộc chiến giữa Batman và Joker, một kẻ tội phạm nguy hiểm và thông minh.',
     'Mỹ', '2008-07-18', 'Christian Bale, Heath Ledger, Aaron Eckhart, Michael Caine', 'Christopher Nolan', 'admin');

####INSERT SEAT_TYPE#######
insert into seat_type(name, description, image, created_by)
    value ('Ghế thường',
           'Ghế thường là loại ghế phổ biến trong rạp chiếu phim, được thiết kế để mang lại sự thoải mái cơ bản cho khán giả.',
           'https://example.com/regular_seat.jpg', 'admin'),
    ('Ghế VIP',
     'Ghế VIP là loại ghế cao cấp trong rạp chiếu phim, thường có không gian rộng rãi hơn và được trang bị các tiện nghi đặc biệt.',
     'https://example.com/vip_seat.jpg', 'admin'),
    ('SweetBox',
     'Ghế đôi là loại ghế dành cho hai người, thường được thiết kế để tạo sự gần gũi và thoải mái cho các cặp đôi.',
     'https://example.com/couple_seat.jpg', 'admin'),
    ('Bean Bag',
     'Ghế Bean Bag là loại ghế có thiết kế mềm mại, thường được sử dụng trong các phòng chiếu phim hiện đại để mang lại sự thoải mái cho khán giả.',
     'https://example.com/bean_bag_seat.jpg', 'admin'),
    ("Lối đi", "Nơi không được đặt ghế", "no-link   ", 'admin');

###INSERT HALL#####
insert into hall(name, width, height, images, description, convenience, created_by)
VALUES ('Phòng chiếu 2D 2', 25, 20,
        JSON_ARRAY('https://example.com/hall4.jpg', 'https://example.com/hall5.jpg', 'https://example.com/hall6.jpg'),
        'Phòng chiếu 2...', 'Hệ thống âm thanh vòm', 'admin'),
       ('Phòng chiếu 2D 1', 20, 15,
        JSON_ARRAY('https://example.com/hall1.jpg', 'https://example.com/hall2.jpg', 'https://example.com/hall3.jpg'),
        'Phòng chiếu 1...', 'Hệ thống âm thanh vòm', 'admin');


####INSERT SHOWTIME#####
insert into showtime(movie_id, hall_id, date, start_time, type, created_by)
    value (1, 1, '2026-06-28', '18:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (2, 1, '2026-06-28', '20:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (5, 1, '2026-06-29', '17:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (6, 1, '2026-06-29', '19:30:00', 'Phụ đề tiếng Việt', 'admin'),
    (3, 2, '2026-07-02', '19:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (4, 2, '2026-07-02', '21:00:00', 'Phụ đề tiếng Việt', 'admin');


######INSERT ROLE#####
insert into role(name, description, created_by)
    value ('Admin', 'Quản trị viên có quyền truy cập đầy đủ vào hệ thống và có thể quản lý tất cả các chức năng.', 'admin'),
    ("User", "Người dùng có quyền truy cập hạn chế, chủ yếu để xem phim và đặt vé.", 'admin');

####INSERT SEAT MAP###- using postman
# [
#     {
#         "seatTypeId":1 ,
#         "rowLabel": "A",
#         "colNumber": 1
#     },
#     {
#         "seatTypeId": 1,
#         "rowLabel": "A",
#         "colNumber": 2
#     },
#         {
#         "seatTypeId": 1,
#         "rowLabel": "A",
#         "colNumber": 3
#     },
#     {
#         "seatTypeId": 1,
#         "rowLabel": "A",
#         "colNumber": 4
#     },
#     {
#         "seatTypeId": 1,
#         "rowLabel": "B",
#         "colNumber": 1
#     },
#     {
#         "seatTypeId": 2,
#         "rowLabel": "B",
#         "colNumber": 2
#     },
#     {
#         "seatTypeId": 2,
#         "rowLabel": "B",
#         "colNumber": 3
#     },
#     {
#         "seatTypeId": 1,
#         "rowLabel": "B",
#         "colNumber": 4
#     },
#     {
#         "seatTypeId": 1,
#         "rowLabel": "C",
#         "colNumber": 1
#     },
#     {
#         "seatTypeId": 1,
#         "rowLabel": "C",
#         "colNumber": 2
#     },
#     {
#         "seatTypeId": 1,
#         "rowLabel": "C",
#         "colNumber": 3
#     },
#     {
#         "seatTypeId": 1,
#         "rowLabel": "C",
#         "colNumber": 4
#     },
#     {
#         "seatTypeId": 3,
#         "rowLabel": "D",
#         "colNumber": 1
#     },
#     {
#         "seatTypeId": 3,
#         "rowLabel": "D",
#         "colNumber": 2
#     },
#     {
#         "seatTypeId": 3,
#         "rowLabel": "D",
#         "colNumber": 3
#     },
#     {
#         "seatTypeId": 3,
#         "rowLabel": "D",
#         "colNumber": 4
#     }
# ]

###