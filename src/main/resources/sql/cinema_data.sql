######INSERT ROLE#####
insert into role(name, description, created_by)
    value ('Admin', 'Quản trị viên có quyền truy cập đầy đủ vào hệ thống và có thể quản lý tất cả các chức năng.',
           'admin'),
    ('User', 'Người dùng có quyền truy cập hạn chế, chủ yếu để xem phim và đặt vé.', 'admin');

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
    ('Lối đi', 'Nơi không được đặt ghế', 'no-link', 'admin');
insert into seat_type(name, description, image, created_by)
    value ('Ghế IMAX',
           'Ghế bọc da sang trọng được bố trí dạng bậc thang với khoảng cách giữa các dãy ghế cực kỳ thoải mái, đảm bảo tầm nhìn tối ưu và sự thư giãn tuyệt đối suốt hành trình điện ảnh.',
           'https://res.cloudinary.com/depaiphq0/image/upload/v1782914838/imax_kkbnrf.jpg', 'admin');
insert into seat_type(name, description, image, created_by)
    value ('Ghế Lagom thường',
           'Ghế da cao cấp từ BoConcept nâng đỡ hoàn hảo, quyền lợi check-in ưu tiên, chăn lông mềm mại và ẩm thực phục vụ tận nơi, kiến tạo sự thư thái cho mọi khoảnh khắc trải nghiệm điện ảnh.',
           'https://res.cloudinary.com/depaiphq0/image/upload/v1782915094/Lagom-2_agplky.jpg', 'admin'),
    ('Ghế Lagom Đôi',
     'Ghế da cao cấp từ BoConcept nâng đỡ hoàn hảo, quyền lợi check-in ưu tiên, chăn lông mềm mại và ẩm thực phục vụ tận nơi, kiến tạo sự thư thái cho mọi khoảnh khắc trải nghiệm điện ảnh.',
     'https://res.cloudinary.com/depaiphq0/image/upload/v1782915097/Lagom03_uegedj.jpg', 'admin');
insert into seat_type(name, description, image, created_by)
    value ('Lối đi',
           'Nơi không được đặt ghế',
           'no-link', 'admin');
####INSERT HALL_TYPE#####
insert into hall_type(name, description, convenience, style, images, created_by)
    value
    ('Phòng chiếu 2D',
     'Phòng chiếu 2D là loại phòng chiếu phim truyền thống, sử dụng công nghệ chiếu phim 2D để hiển thị hình ảnh trên màn hình. Phòng chiếu 2D thường có chất lượng hình ảnh và âm thanh tốt, phù hợp cho các bộ phim không yêu cầu hiệu ứng 3D.',
     'Hệ thống âm thanh vòm, Ghế ngồi thoải mái, Màn hình chất lượng cao',
     'Thông thường',
     JSON_ARRAY('https://example.com/hall_type_2d_1.jpg', 'https://example.com/hall_type_2d_2.jpg'), 'admin'),
    ('IMAX',
     'IMAX là công nghệ chiếu phim tiên tiến nhất trên thế giới hiện nay, từ thiết kế phòng chiếu vô cùng choáng ngợp đến hiệu ứng âm thanh và hình ảnh đỉnh cao.',
     'Công nghệ IMAX with Laser tiên phong
     Mang đến hình ảnh 4K sắc nét vượt trội với tỷ lệ màn hình mở rộng 1.43:1 hoặc 1.90:1, tốc độ khung hình lên đến 120fps, độ tương phản sâu và màu sắc sống động, khiến khán giả như bước vào bên trong bộ phim.
     Âm thanh IMAX thế hệ mới – nhập vai toàn diện
     Hệ thống âm thanh đa kênh chính xác, dải động lớn và được tinh chỉnh đồng đều khắp không gian rạp, tạo trải nghiệm âm thanh sống động, bao trùm và chân thực đồng hành cùng hình ảnh đỉnh cao.
     Ghế da cao cấp & thiết kế bậc thang rộng rãi
     Ghế bọc da sang trọng được bố trí dạng bậc thang với khoảng cách giữa các dãy ghế cực kỳ thoải mái, đảm bảo tầm nhìn tối ưu và sự thư giãn tuyệt đối suốt hành trình điện ảnh.',
     'Công nghệ',
     JSON_ARRAY('https://res.cloudinary.com/depaiphq0/image/upload/v1782914513/imax-1_xp6oh6.webp',
                'https://res.cloudinary.com/depaiphq0/image/upload/v1782914513/imax-2_zzuipz.jpg',
                'https://res.cloudinary.com/depaiphq0/image/upload/v1782914514/imax-3_etcojt.jpg',
                'https://res.cloudinary.com/depaiphq0/image/upload/v1782914514/imax-5_blwdtd.jpg',
                'https://res.cloudinary.com/depaiphq0/image/upload/v1782914514/imax-4_g3wnyg.jpg'), 'admin');
insert into hall_type(name, description, convenience, style, images, created_by)
    value ('Lagom',
           'Bước vào LAGOM là bước vào một thế giới nơi công nghệ hòa quyện tinh tế cùng nghệ thuật cảm xúc. Lấy cảm hứng từ triết lý LAGOM của Thụy Điển - Vừa đủ, Cân bằng và trọn vẹn, không gian phòng chiếu được bao phủ bởi những gam màu be nhẹ nhàng, ấm áp, tạo nên một không khí thanh lịch, trầm ổn, nơi mọi ồn ào của cuộc sống dường như được bỏ lại phía sau cánh cửa. Công nghệ trình chiếu sử dụng hệ thống máy chiếu tiên tiến cùng âm thanh Dolby 7.1 sống động nhưng hòa quyện tuyệt vời để không phá vỡ sự tĩnh tại của thiết kế phòng chiếu.
             Ghế da tại LAGOM được cung cấp bởi BoConcept, hỗ trợ ôm sát và nâng đỡ cơ thể hoàn hảo, đi kèm với chất lượng dịch vụ chuyên biệt cho các phòng chiếu cao cấp:
             Quyền lợi check in ưu tiên và tận hưởng tiện nghi riêng biệt trong khi chờ đợi
             Ghế da cao cấp đa chế độ từ ngồi đến nằm, thoải mái tận hưởng suốt thời gian xem phim
             Ấm áp với chăn lông mềm mại tại ghế ngồi
             Ẩm thực được phục vụ tận nơi ',
           'Không gian cân bằng theo triết lý sống vừa đủ
             LAGOM được bao phủ bởi gam màu be nâu ấm áp, trầm ổn và tinh tế, nơi mọi ồn ào cuộc sống dường như bỏ lại phía sau.
             Công nghệ trình chiếu đỉnh cao
             Hệ thống máy chiếu Laser tiên tiến kết hợp âm thanh Dolby 7.1 sống động mang đến trải nghiệm hình ảnh sắc nét, âm thanh sống động nhưngvẫn giữ trọn sự yên bình nội tại.
             Ghế da từ BoConcept cùng dịch vụ đẳng cấp
             Ghế da cao cấp từ BoConcept nâng đỡ hoàn hảo, quyền lợi check-in ưu tiên, chăn lông mềm mại và ẩm thực phục vụ tận nơi, kiến tạo sự thư thái cho mọi khoảnh khắc trải nghiệm điện ảnh.',
           'Phong cách',
           JSON_ARRAY('https://res.cloudinary.com/depaiphq0/image/upload/v1782915041/Lagom1_zhiqak.jpg',
                      'https://res.cloudinary.com/depaiphq0/image/upload/v1782915042/Lagom03_pthmtp.jpg',
                      'https://res.cloudinary.com/depaiphq0/image/upload/v1782915042/Lagom04_r0fbu2.jpg',
                      'https://res.cloudinary.com/depaiphq0/image/upload/v1782915043/Lagom-1_wanjxw.jpg',
                      'https://res.cloudinary.com/depaiphq0/image/upload/v1782915043/Lagom-2_lxjc0j.jpg'), 'admin');

####INSERT HALL#####
insert into hall(hall_type_id, name, width, height, created_by)
value(1,'SCREEN1','5','4','admin');

####INSERT SEAT #####
insert into seat(hall_id, seat_type_id, row_label, col_number, created_by)
value
    (1,1,'A',1,'admin'),
    (1,1,'A',2,'admin'),
    (1,1,'KO',3,'admin'),
    (1,1,'A',4,'admin'),
    (1,1,'A',5,'admin'),

    (1,1,'B',1,'admin'),
    (1,2,'B',2,'admin'),
    (1,1,'KO',3,'admin'),
    (1,2,'B',4,'admin'),
    (1,1,'B',5,'admin'),

    (1,1,'C',1,'admin'),
    (1,2,'C',2,'admin'),
    (1,1,'KO',3,'admin'),
    (1,2,'C',4,'admin'),
    (1,1,'C',5,'admin'),

    (1,3,'D',1,'admin'),
    (1,3,'D',2,'admin'),
    (1,1,'KO',3,'admin'),
    (1,3,'D',4,'admin'),
    (1,3,'D',5,'admin');


####INSERT SHOWTIME#####
insert into showtime(movie_id, hall_id, date, start_time, type, created_by)
    value (1, 1, '2026-06-28', '18:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (2, 1, '2026-06-28', '20:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (5, 1, '2026-06-29', '17:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (6, 1, '2026-06-29', '19:30:00', 'Phụ đề tiếng Việt', 'admin'),
    (3, 2, '2026-07-02', '19:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (4, 2, '2026-07-02', '21:00:00', 'Phụ đề tiếng Việt', 'admin');
