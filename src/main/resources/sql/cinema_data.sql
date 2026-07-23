###INSERT HALL_TYPE_IMAGE#####
insert into hall_type_image(hall_type_id, image_url, created_by)
    value (2, 'https://res.cloudinary.com/depaiphq0/image/upload/v1782914513/imax-1_xp6oh6.webp', 'admin'),
    (2, 'https://res.cloudinary.com/depaiphq0/image/upload/v1782914513/imax-2_zzuipz.jpg', 'admin'),
    (2, 'https://res.cloudinary.com/depaiphq0/image/upload/v1782914514/imax-3_etcojt.jpg', 'admin'),
    (2, 'https://res.cloudinary.com/depaiphq0/image/upload/v1782914514/imax-5_blwdtd.jpg', 'admin'),
    (2, 'https://res.cloudinary.com/depaiphq0/image/upload/v1782914514/imax-4_g3wnyg.jpg', 'admin');

insert into hall_type_image(hall_type_id, image_url, created_by)
    value (3, 'https://res.cloudinary.com/depaiphq0/image/upload/v1782915041/Lagom1_zhiqak.jpg', 'admin'),
    (3, 'https://res.cloudinary.com/depaiphq0/image/upload/v1782915042/Lagom03_pthmtp.jpg', 'admin'),
    (3, 'https://res.cloudinary.com/depaiphq0/image/upload/v1782915042/Lagom04_r0fbu2.jpg', 'admin'),
    (3, 'https://res.cloudinary.com/depaiphq0/image/upload/v1782915043/Lagom-1_wanjxw.jpg', 'admin'),
    (3, 'https://res.cloudinary.com/depaiphq0/image/upload/v1782915043/Lagom-2_lxjc0j.jpg', 'admin');


####INSERT PAYMENT METHOD####
INSERT INTO payment_method(code, name, description, logo, surcharge, created_by)
VALUES ('MOMO', 'Ví MoMo', 'Thanh toán nhanh chóng và bảo mật qua Ví điện tử MoMo.',
        'https://res.cloudinary.com/depaiphq0/image/upload/v1783786591/images_zme00b.png', 0.00, 'admin'),
       ('VNPAY', 'Cổng thanh toán VNPAY',
        'Thanh toán quét mã QR qua ứng dụng ngân hàng hoặc thẻ ATM nội địa, thẻ quốc tế.',
        'https://res.cloudinary.com/depaiphq0/image/upload/v1783786625/images_smqnem.jpg', 0.00, 'admin'),
       ('ZALOPAY', 'Ví ZaloPay', 'Thanh toán trực tuyến dễ dàng và tiện lợi bằng ứng dụng Ví điện tử ZaloPay.',
        'https://res.cloudinary.com/depaiphq0/image/upload/v1783786655/images_kdfjks.png', 0.00, 'admin'),
       ('BANK_CARD', 'Thẻ ngân hàng (ATM/Visa/Mastercard)',
        'Thanh toán trực tiếp bằng các loại thẻ ngân hàng nội địa (ATM) hoặc quốc tế (Visa, Mastercard, JCB).',
        'https://res.cloudinary.com/depaiphq0/image/upload/v1783786695/images_gqzrse.jpg', 0.00, 'admin');


####INSERT PRODUCT
INSERT INTO product(name, description, image, price, created_by)
VALUES ('Combo 3',
        '\"Chia sẻ niềm vui với bạn bè! Combo 3 gồm 2 bắp rang bơ, 3 Pepsi mát lạnh – tiết kiệm hơn 52,000!',
        'https://res.cloudinary.com/depaiphq0/image/upload/v1783169044/combo-3_xphbsr.webp',
        155000, 'admin'),
       ('Combo 4',
        '\"Thêm bạn, thêm vui! Combo 4 mang đến 3 bắp rang bơ, 4 Pepsi mát lạnh– tiết kiệm hơn 95,000!',
        'https://res.cloudinary.com/depaiphq0/image/upload/v1783169044/combo-4_ygfapo.webp',
        235000, 'admin'),
       ('Combo 1 Big Extra Premium',
        '\"Thỏa mãn cơn thèm\" với 1 phần bắp rang bơ thơm ngon, 1 Pepsi mát lạnh và 1 gói snack Premium tuỳ chọn!',
        'https://res.cloudinary.com/depaiphq0/image/upload/v1783169044/Combo-1_qnatzk.webp',
        120000, 'admin'),
       ('Combo 2 Big Extra Premium',
        '\"Nhân đôi sự sảng khoái! Combo gồm 1 bắp rang bơ lớn, 2 Pepsi cỡ lớn + 1 snack Premium tuỳ chọn– tiết kiệm hơn 33,000!',
        'https://res.cloudinary.com/depaiphq0/image/upload/v1783169044/co-combo-2-extra-premium_ds9dws.webp',
        135000, 'admin'),
       ('Teanema Combo 1 Extra',
        '1 Bắp ngọt + 1 Trà Galaxy',
        'https://res.cloudinary.com/depaiphq0/image/upload/v1783169044/co-teanema-1-combo-extra_ku3rmi.png',
        119000, 'admin'),
       ('Combo 1 Ovaltine Extra Premium',
        '1 Bắp ngọt + 1 Ovaltine 22oz + 1 Snack Premium',
        'https://res.cloudinary.com/depaiphq0/image/upload/v1783169044/co-combo-1-ovaltine-premium_ntbbcm.webp',
        120000, 'admin');

######INSERT ROLE#####
insert into role(name, description, created_by)
    value ('Admin', 'Quản trị viên có quyền truy cập đầy đủ vào hệ thống và có thể quản lý tất cả các chức năng.',
           'admin'),
    ('User', 'Người dùng có quyền truy cập hạn chế, chủ yếu để xem phim và đặt vé.', 'admin');

###INSERT GENRE#######
insert into genre(name, description, created_by)
values ('Hành động',
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
       ('Giả tưởng',
        'Phim khoa học viễn tưởng là thể loại phim tập trung vào những câu chuyện về công nghệ, không gian, thời gian, và các hiện tượng siêu nhiên. Những bộ phim này thường có những cảnh quay đẹp mắt và những tình tiết ly kỳ.',
        'admin'),
       ('Tâm lý',
        'Phim tâm lý tập trung vào thế giới nội tâm nhân vật, những câu chuyện tình cảm, tâm lý xã hội sâu sắc.',
        'admin'),
       ('Hoạt hình',
        'Phim hoạt hình sử dụng các hình vẽ hoặc mô hình chuyển động để kể chuyện, phù hợp với cả trẻ em và người lớn, thường mang thông điệp nhân văn sâu sắc.',
        'admin'),
       ('Gia đình',
        'Phim gia đình tập trung vào các chủ đề mối quan hệ giữa các thành viên trong gia đình, mang tính giáo dục, ấm áp và phù hợp cho mọi lứa tuổi xem cùng nhau.',
        'admin'),
       ('Giật gân',
        'Phim giật gân (Thriller) sử dụng các yếu tố hồi hộp, căng thẳng, bất ngờ để kích thích sự tò mò và cảm giác hồi hộp của người xem đến tận phút cuối.',
        'admin'),
       ('Kỳ ảo',
        'Phim kỳ ảo (Fantasy) lấy bối cảnh trong các thế giới tưởng tượng thần thoại, phép thuật, các sinh vật huyền bí và những cuộc phiêu lưu siêu nhiên.',
        'admin'),
       ('Nhạc kịch',
        'Phim nhạc kịch kết hợp các bài hát và điệu nhảy do nhân vật biểu diễn như một phần cốt lõi để kể câu chuyện và thể hiện cảm xúc.',
        'admin'),
       ('Lịch sử',
        'Phim lịch sử tái hiện lại các sự kiện, nhân vật hoặc thời kỳ có thật trong lịch sử nhằm cung cấp cái nhìn chân thực về quá khứ.',
        'admin'),
       ('Chiến tranh',
        'Phim chiến tranh tập trung khai thác đề tài xung đột vũ trang, cuộc sống người lính ở tiền tuyến hoặc hậu phương và những ảnh hưởng tàn khốc của chiến tranh.',
        'admin'),
       ('Bí ẩn',
        'Phim bí ẩn (Mystery) xoay quanh quá trình điều tra, giải mã một vụ án hoặc một sự kiện kỳ lạ, thu hút người xem cùng tham gia phá án.',
        'admin'),
       ('Tội phạm',
        'Phim tội phạm khai thác các đề tài liên quan đến hoạt động phi pháp, giới giang hồ, băng đảng và công việc của các lực lượng phòng chống tội phạm.',
        'admin'),
       ('Tiểu sử',
        'Phim tiểu sử kể về cuộc đời của một nhân vật có thật trong lịch sử hoặc thời đương đại, giúp người xem thấu hiểu hành trình cuộc đời họ.',
        'admin'),
       ('Tài liệu',
        'Phim tài liệu ghi lại những hình ảnh, sự kiện có thật ngoài đời sống nhằm phản ánh chân thực một khía cạnh xã hội, khoa học hoặc tự nhiên.',
        'admin');


###INSERT MOVIE#######
insert into movie(id, title, duration, avatar, trailer, description, country, age_limit, premiere_date, rating, actors,
                  director, status, created_by)
values (1, 'Minions & Quái Vật', 90,
        'https://res.cloudinary.com/depaiphq0/image/upload/v1782959342/500-minions_1782791874318_llizmp.webp',
        'https://www.youtube.com/embed/ZXAcQeaTq0Q?si=hS81fuivsnFOUSDt',
        'Minions & Quái Vật là câu chuyện vừa náo loạn, vừa ngớ ngẩn nhưng “hoàn toàn có thật” về cách Minions chinh phục Hollywood, trở thành ngôi sao điện ảnh, rồi mất tất cả, vô tình thả quái vật ra khắp thế giới và sau đó phải cùng nhau hợp sức để cứu lấy hành tinh khỏi chính mớ hỗn loạn mà mình tạo ra.',
        'Mỹ', 0, '2026-07-01 00:00:00', 9.3, 'Pierre Coffin, Chistoph Waltz, Jeff Bridges, Jesse Eisenberg',
        'Pierre Coffin', 'NOW_SHOWING', 'admin'),

       (2, 'Backrooms: Thực Thể Quỷ Quyệt', 110,
        'https://res.cloudinary.com/depaiphq0/image/upload/v1782986380/backrooms-500_1781235880443_pjhlel.jpg',
        'https://www.youtube.com/embed/VnJ5eZcW3fU?si=ezUy5Ifvaz9EB4fJ',
        'Backrooms theo chân Clark (Chiwetel Ejiofor), một chủ cửa hàng nội thất, vô tình phát hiện cánh cửa bí ẩn dưới tầng hầm. Bước qua đó, anh bị cuốn vào một chiều không gian vô tận với những căn phòng màu vàng méo mó, liên tục lặp lại. Khi Clark ngày càng lún sâu và ám ảnh, nhà trị liệu tâm lý của anh là Mary (Renate Reinsve) quyết định bước vào không gian đó để tìm và giải cứu anh.',
        'Mỹ', 16, '2026-06-25 00:00:00', 8.2, 'Chiwetel Ejiofor, Renate Reinsve, Mark Duplass', 'Kane Parsons',
        'NOW_SHOWING', 'admin'),

       (3, 'Colony', 122,
        'https://res.cloudinary.com/depaiphq0/image/upload/v1782987286/colony-500_1780479019386_vii0bp.webp ',
        'https://www.youtube.com/embed/YYFycg2c09o?si=f8gGTVDiN2t-htS-',
        'Khi một dịch bệnh bí ẩn bùng phát tại tòa cao ốc giữa trung tâm Seoul, những người sống sót bị mắc kẹt và buộc phải chiến đấu để thoát thân. Những người đã nhiễm bệnh không chỉ là những xác sống mất não thông thường - chúng đang tiến hóa và săn mồi theo bầy đàn có tổ chức. Hy vọng duy nhất của những người sống sót phụ thuộc vào một gã đàn ông tự nhận đang mang trong mình loại vắc-xin có thể chấm dứt đại dịch. Trong cuộc chạy trốn nghẹt thở lên sân thượng, ranh giới giữa con người và quái vật dần sụp đổ.',
        'Hàn Quốc', 16, '2026-06-10 00:00:00', 9.0, 'Ji Chang Wook, Jun Ji Huyn, Koo Kyo Hwan', 'Yeon Sang Ho',
        'NOW_SHOWING', 'admin'),

       (4, 'Ám Ảnh', 109,
        'https://res.cloudinary.com/depaiphq0/image/upload/v1782986406/obsession-500_1781492667428_l7anyg.jpg ',
        'https://www.youtube.com/embed/rZF4vNv36Dw?si=SwG-OA3m4t2gmxQN',
        'Bear, một chàng trai si tình, đã bẻ gãy món đồ chơi bí ẩn mang tên "Liễu Ước Nguyện" để đổi lấy tình yêu của cô gái mình thầm thương. Điều ước nhanh chóng trở thành hiện thực, nhưng hạnh phúc mà anh hằng mong đợi lại dần biến thành cơn ác mộng. Bear dần nhận ra một sự thật rùng rợn: cái giá phải trả cho món quà kỳ diệu đó kinh hoàng và đen tối hơn bất cứ điều gì anh có thể tưởng tượng.',
        'Mỹ', 18, '2026-06-19 00:00:00', 8.7, 'Michael Johnton, Inde Navarrette, Cooper Tomlinson', 'Curry Barker',
        'NOW_SHOWING', 'admin'),

       (5, 'Mesdames Thanh Sắc', 125,
        'https://res.cloudinary.com/depaiphq0/image/upload/v1782988106/madames-thanh-sac-500_1781496606189_ost45k.jpg ',
        'https://www.youtube.com/embed/enNVcwigZg4?si=tWVi_A134WYitgOd',
        'Mesdames Thanh Sắc xoay quanh cuộc đời của đại mỹ nhân Cầm Thanh (Thanh Hằng) và Madame Sắc (Hồng Ánh) - bà chủ vũ trường Kim Đô vô cùng giàu có và sở hữu nhiều kim cương. Dù ở dưới trướng của bà Sắc và từng bước trở thành vũ nữ đình đám nhất Sài Gòn những năm 1960, nhưng Cầm Thanh luôn muốn nổi loạn. Từ đó, hai người phụ nữ bắt đầu cuộc giằng co căng thẳng dẫn đến những sự kiện gây rúng động.',
        'Việt Nam', 18, '2026-06-18 00:00:00', 8.3, 'Hồng Ánh, Thanh Hằng, Lương Thế Thành', 'Thắng Vũ', 'NOW_SHOWING',
        'admin'),

       (6, 'Supergirl', 108,
        'https://res.cloudinary.com/depaiphq0/image/upload/v1782986409/supergirl-500_1780456971440_gozvxe.webp ',
        'https://www.youtube.com/embed/zb37oinmNQM?si=Kn_dom2_5--4c4jM',
        'Supergirl – bom tấn mới nhất từ DC Studios – sẽ chính thức đổ bộ các rạp chiếu toàn cầu vào mùa hè này, với Milly Alcock đảm nhận vai kép Supergirl/Kara Zor-El. Khi một kẻ thù bất ngờ và tàn nhẫn giáng đòn ngay tại nơi cô gọi là nhà, Kara Zor-El – hay còn được biết đến với cái tên Supergirl – buộc phải bắt tay với một đồng minh không ai ngờ tới, bắt đầu chuyến hành trình xuyên dải ngân hà đầy sử thi, nơi vừa là cuộc trả thù, vừa là hành trình đi tìm công lý.',
        'Mỹ', 13, '2026-06-24 00:00:00', 8.6, 'Milly Alcock, Matthias Schoenaerts, Eve Ridley, Jason Momoa',
        'Craig Gillespie', 'NOW_SHOWING', 'admin'),

       (7, 'Câu Chuyện Đồ Chơi 5', 102,
        'https://res.cloudinary.com/depaiphq0/image/upload/v1782986414/toy-story-5-500_1780307783575_iosmtm.jpg ',
        'https://www.youtube.com/embed/BXN2fTDtak8?si=C-YN9FnqRiwke8r3',
        'Các món đồ chơi đã trở lại trong Toy Story 5 của Disney và Pixar, và lần này sẽ là cuộc đối đầu giữa đồ chơi và công nghệ. Buzz, Woody, Jessie cùng cả nhóm đồ chơi quen thuộc sẽ phải đối mặt với thử thách khó khăn hơn gấp bội khi chạm trán một mối đe dọa ảnh hưởng đến toàn bộ thế giới đồ chơi.',
        'Mỹ', 0, '2026-06-17 00:00:00', 8.8, 'Tom Hanks, Keanu Reeves, Bonnie Hunt', 'Andrew Stanton', 'NOW_SHOWING',
        'admin'),

       (8, 'Tung Hoành Tứ Hải', 109,
        'https://res.cloudinary.com/depaiphq0/image/upload/v1782986421/500_1782200524605_sogjyb.webp ',
        'https://www.youtube.com/embed/EBIqnaRbVHs?si=ZGNAIxu4P0qSmY7l',
        'Tung Hoành Tứ Hải có thể xem là "Phong Trần Tam Hiệp" phiên bản siêu trộm thời hiện đại. Được "cầm trịch" bởi đạo diễn gạo cội Ngô Vũ Sâm- người định hình phong cách hành động "Gun-fu" cùng thể loại "Tam kiệt", Tung Hoành Tứ Hải kể về nhóm "đạo tặc" do Châu Nhuận Phát dẫn đầu trên đường thực hiện phi vụ cuối cùng trước khi giải nghệ.
   Khung hình bộ ba Châu Nhuận Phát - Trương Quốc - Chung Sở Hồng chu du khắp nơi trên xe mui trần đánh dấu khoảnh khắc kinh điển của điện ảnh Hương Cảng và cũng đại diện cho thời hoàng kim thống trị Châu Á của phim Hongkong.',
        'Hồng Kong', 16, '2026-06-26 00:00:00', 7.9, 'Châu Nhuật Phát, Trương Quốc Vĩnh, Chung Sở Hồng', 'Ngô Vũ Sâm',
        'NOW_SHOWING', 'admin'),

       (9, 'Ngày Con Sống Lại', 126,
        'https://res.cloudinary.com/depaiphq0/image/upload/v1782986499/sheep-in-the-box-2_1782790582072_zfj4o1.webp ',
        'https://www.youtube.com/embed/3guvSfX3HKQ?si=j5M56FsdCUwRZg3i',
        'Đạo diễn Kore-eda trở lại với một tác phẩm giả tưởng đầy cảm động. Một cặp vợ chồng đau đớn trải qua nỗi đau mất con. Để lấp đầy khoảng trống trong tổ ấm nhỏ, cả hai quyết định nhận nuôi một rô-bốt hình người hiện đại, chăm sóc nó như con trai ruột.
   Nếu một cỗ máy có khả năng biểu đạt cảm xúc, trao đi và nhận lại tình yêu, liệu sợi dây kết nối này có kém phần thiêng liêng hơn huyết thống?',
        'Nhật Bản', 13, '2026-07-05 00:00:00', 0.0, 'Ayase Haruka, Yamamoto Daigo, Kuwaki Rimu', 'Hirokazu Kore-Eda',
        'COMING_SOON', 'admin'),

       (10, 'Moana', 120,
        'https://res.cloudinary.com/depaiphq0/image/upload/v1782986478/moana-500_1774495216480_uxedda.jpg ',
        'https://www.youtube.com/embed/hTZT2vdFHp4?si=j6bO-BUp-FZ24Am9',
        'Trong Moana, phiên bản live-action tái hiện lại cuộc phiêu lưu hoạt hình được yêu mến và từng được đề cử Oscar® của Disney, Moana (Catherine Lagaʻaia) đáp lại tiếng gọi của đại dương và lần đầu tiên rời xa rạn san hô bao quanh hòn đảo Motunui để lên đường cùng á thần huyền thoại Maui (Dwayne Johnson) trong một hành trình phi thường nhằm khôi phục sự thịnh vượng cho dân tộc mình.
   Bộ phim được đạo diễn bởi Thomas Kail, người từng giành giải Emmy® và Tony Award® (Hamilton); sản xuất bởi Dwayne Johnson, Dany Garcia, Beau Flynn, Hiram Garcia và Lin-Manuel Miranda; đồng thời được điều hành sản xuất bởi Kail, Scott Sheldon, Charles Newirth và Auliʻi Cravalho – người từng lồng tiếng cho Moana trong hai phần phim hoạt hình Moana và Moana 2.
   Moana quy tụ các ca khúc gốc do Lin-Manuel Miranda, Opetaia Foaʻi và Mark Mancina sáng tác, cùng phần nhạc nền nguyên bản do Mark Mancina đảm nhiệm. Khán giả sẽ được đắm mình trong những khung hình mãn nhãn, âm thanh sống động và các giai điệu cuốn hút của Moana',
        'Mỹ', 0, '2026-07-10 00:00:00', 0.0, 'Dwayne Johnson, Catherine Laga’aia, Frankie Adams', 'Thomas Kail',
        'COMING_SOON', 'admin'),

       (11, 'The Odyssey', 173,
        'https://res.cloudinary.com/depaiphq0/image/upload/v1782986484/the-oddysey-500_1778060370554_jmcxsg.jpg ',
        'https://www.youtube.com/embed/hk0JVCJ8NOA?si=gGu7z-1s_NDVx-QC',
        'Theo chân Odysseus trong hành trình trở về nhà sau cuộc chiến thành Troy, tràn ngập thử thách như cuộc chạm trán của ông với Polyphemus, các nàng tiên cá, Circe và kết thúc bằng cuộc đoàn tụ với vợ mình, Penelope.',
        'Mỹ', 0, '2026-07-17 00:00:00', 0.0,
        'Matt Damon, Tom Holland, Charlize Theron, Anne Hathaway, Jon Bernthal, Zendaya, Lupita Nyong’o, Robert Pattinson',
        'Christopher Nolan', 'COMING_SOON', 'admin'),

       (12, 'Phim Điện Ảnh Thám Tử Lừng Danh Conan: Thiên Thần Sa Ngã Trên Xa Lộ', 109,
        'https://res.cloudinary.com/depaiphq0/image/upload/v1782986487/detective-conan-fallen-angel-of-the-highway-51_1781578881102_zuzncz.jpg ',
        'https://www.youtube.com/embed/bR5YyPRsycw?si=4RopcPoZe7fKUHu9',
        'Thám Tử Lừng Danh Conan: Thiên Thần Sa Ngã Trên Xa Lộ là cuộc phiêu lưu thứ 29 trên màn ảnh rộng của Conan, lần này thám tử nhí sẽ xuất hiện cùng nhân vật mới - “Nữ Thần Gió” Chihaya.
   Conan, gia đình Ran, Sonoko, đội thám tử nhí cùng Sera Masumi đến khu vực Minatomirai, Yokohama để tham dự “Lễ hội Moto Kanagawa”. Sự kiện náo nhiệt này nhanh chóng bị khuấy động bởi tay lái bí ẩn mang biệt danh “Quái Xế Đen”. Hắn phóng xe với tốc độ kinh hoàng, lao vọt lên nóc chiếc xe chở nhóm Conan rồi biến mất trong chớp mắt, khiến ngay cả nữ cảnh sát giao thông nổi tiếng với danh xưng “Nữ Thần Gió” của Sở Cảnh sát Kanagawa - Hagiwara Chihaya - cũng không thể truy đuổi kịp.
   Trong khi cuộc săn lùng vẫn rơi vào bế tắc, tại hội trường lễ hội lại diễn ra màn ra mắt mẫu moto công nghệ cao màu trắng mang tên “Angel”. Điều đáng ngờ là chiếc xe của “Quái Xế Đen” lại sở hữu thiết kế gần như giống hệt mẫu xe này. Bỗng dưng, nữ cảnh sát Chihaya chợt nhớ về những ký ức tưởng chừng đã ngủ yên về người em trai quá cố Hagiwara Kenji và người bạn thân cùng khóa của anh, Matsuda Jinpei. Danh tính thực sự của kẻ cầm lái và mục đích đằng sau những cuộc rượt đuổi đầy nguy hiểm ấy kéo Conan và nữ cảnh sát Chihaya vào một cuộc đấu trí, khơi dậy một vụ án của nhiều năm trước.',
        'Nhật Bản', 13, '2026-07-18 00:00:00', 0.0,
        'Hayashibara Megumi, Takayama Minami, Yamazaki Wakana, Koyama Rikiya', 'Takahiro Hasui', 'COMING_SOON',
        'admin');

###INSERT MOVIE_GENRE#######
insert into movie_genre(movie_id, genre_id)
values (1, 8),
       (1, 2),
       (2, 4),
       (2, 10),
       (3, 4),
       (3, 10),
       (4, 4),
       (4, 10),
       (5, 7),
       (5, 5),
       (5, 16),
       (6, 1),
       (6, 2),
       (6, 6),
       (7, 8),
       (7, 3),
       (7, 9),
       (8, 1),
       (9, 7),
       (9, 6),
       (9, 9),
       (10, 2),
       (10, 9),
       (10, 3),
       (11, 1),
       (11, 2),
       (11, 13),
       (12, 8);


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
    value (1, 'SCREEN1', '5', '4', 'admin');

insert into hall(hall_type_id, name, width, height, created_by)
    value (1, 'SCREEN2', 13, 15, 'admin'),
    (2, 'SREEN1', 22, 16, 'admin');


####INSERT SEAT #####
insert into seat(hall_id, seat_type_id, row_label, col_number, created_by)
    value
    (1, 1, 'A', 1, 'admin'),
    (1, 1, 'A', 2, 'admin'),
    (1, 1, 'KO', 3, 'admin'),
    (1, 1, 'A', 4, 'admin'),
    (1, 1, 'A', 5, 'admin'),
    (1, 1, 'B', 1, 'admin'),
    (1, 2, 'B', 2, 'admin'),
    (1, 1, 'KO', 3, 'admin'),
    (1, 2, 'B', 4, 'admin'),
    (1, 1, 'B', 5, 'admin'),
    (1, 1, 'C', 1, 'admin'),
    (1, 2, 'C', 2, 'admin'),
    (1, 1, 'KO', 3, 'admin'),
    (1, 2, 'C', 4, 'admin'),
    (1, 1, 'C', 5, 'admin'),
    (1, 3, 'D', 1, 'admin'),
    (1, 3, 'D', 2, 'admin'),
    (1, 1, 'KO', 3, 'admin'),
    (1, 3, 'D', 4, 'admin'),
    (1, 3, 'D', 5, 'admin');

INSERT INTO seat (hall_id, seat_type_id, row_label, col_number, created_by)
VALUES
    -- ==========================================
    -- KHU VỰC GHẾ THƯỜNG (Màu xám nhạt)
    -- ==========================================
    -- Hàng A
    (3, 1, 'A', 1, 'admin'),
    (3, 1, 'A', 2, 'admin'),
    (3, 1, 'A', 3, 'admin'),
    (3, 1, 'A', 4, 'admin'),
    (3, 1, 'A', 5, 'admin'),
    (3, 1, 'A', 6, 'admin'),
    (3, 1, 'KO', 7, 'admin'),
    (3, 1, 'A', 8, 'admin'),
    (3, 1, 'A', 9, 'admin'),
    (3, 1, 'A', 10, 'admin'),
    (3, 1, 'A', 11, 'admin'),
    (3, 1, 'A', 12, 'admin'),
    (3, 1, 'A', 13, 'admin'),

    -- Hàng B
    (3, 1, 'B', 1, 'admin'),
    (3, 1, 'B', 2, 'admin'),
    (3, 1, 'B', 3, 'admin'),
    (3, 1, 'B', 4, 'admin'),
    (3, 1, 'B', 5, 'admin'),
    (3, 1, 'B', 6, 'admin'),
    (3, 1, 'KO', 7, 'admin'),
    (3, 1, 'B', 8, 'admin'),
    (3, 1, 'B', 9, 'admin'),
    (3, 1, 'B', 10, 'admin'),
    (3, 1, 'B', 11, 'admin'),
    (3, 1, 'B', 12, 'admin'),
    (3, 1, 'B', 13, 'admin'),

    -- Hàng C
    (3, 1, 'C', 1, 'admin'),
    (3, 1, 'C', 2, 'admin'),
    (3, 1, 'C', 3, 'admin'),
    (3, 1, 'C', 4, 'admin'),
    (3, 1, 'C', 5, 'admin'),
    (3, 1, 'C', 6, 'admin'),
    (3, 1, 'KO', 7, 'admin'),
    (3, 1, 'C', 8, 'admin'),
    (3, 1, 'C', 9, 'admin'),
    (3, 1, 'C', 10, 'admin'),
    (3, 1, 'C', 11, 'admin'),
    (3, 1, 'C', 12, 'admin'),
    (3, 1, 'C', 13, 'admin'),

    -- Hàng D
    (3, 1, 'D', 1, 'admin'),
    (3, 1, 'D', 2, 'admin'),
    (3, 1, 'D', 3, 'admin'),
    (3, 1, 'D', 4, 'admin'),
    (3, 1, 'D', 5, 'admin'),
    (3, 1, 'D', 6, 'admin'),
    (3, 1, 'KO', 7, 'admin'),
    (3, 1, 'D', 8, 'admin'),
    (3, 1, 'D', 9, 'admin'),
    (3, 1, 'D', 10, 'admin'),
    (3, 1, 'D', 11, 'admin'),
    (3, 1, 'D', 12, 'admin'),
    (3, 1, 'D', 13, 'admin'),

    -- Hàng E
    (3, 1, 'E', 1, 'admin'),
    (3, 1, 'E', 2, 'admin'),
    (3, 1, 'E', 3, 'admin'),
    (3, 1, 'E', 4, 'admin'),
    (3, 1, 'E', 5, 'admin'),
    (3, 1, 'E', 6, 'admin'),
    (3, 1, 'KO', 7, 'admin'),
    (3, 1, 'E', 8, 'admin'),
    (3, 1, 'E', 9, 'admin'),
    (3, 1, 'E', 10, 'admin'),
    (3, 1, 'E', 11, 'admin'),
    (3, 1, 'E', 12, 'admin'),
    (3, 1, 'E', 13, 'admin'),

    -- Hàng F
    (3, 1, 'F', 1, 'admin'),
    (3, 1, 'F', 2, 'admin'),
    (3, 1, 'F', 3, 'admin'),
    (3, 1, 'F', 4, 'admin'),
    (3, 1, 'F', 5, 'admin'),
    (3, 1, 'F', 6, 'admin'),
    (3, 1, 'KO', 7, 'admin'),
    (3, 1, 'F', 8, 'admin'),
    (3, 1, 'F', 9, 'admin'),
    (3, 1, 'F', 10, 'admin'),
    (3, 1, 'F', 11, 'admin'),
    (3, 1, 'F', 12, 'admin'),
    (3, 1, 'F', 13, 'admin'),

    -- Hàng G
    (3, 1, 'G', 1, 'admin'),
    (3, 1, 'G', 2, 'admin'),
    (3, 1, 'G', 3, 'admin'),
    (3, 1, 'G', 4, 'admin'),
    (3, 1, 'G', 5, 'admin'),
    (3, 1, 'G', 6, 'admin'),
    (3, 1, 'KO', 7, 'admin'),
    (3, 1, 'G', 8, 'admin'),
    (3, 1, 'G', 9, 'admin'),
    (3, 1, 'G', 10, 'admin'),
    (3, 1, 'G', 11, 'admin'),
    (3, 1, 'G', 12, 'admin'),
    (3, 1, 'G', 13, 'admin'),

    -- ==========================================
    -- KHU VỰC GHẾ VIP (Màu hồng nhạt)
    -- ==========================================
    -- Hàng H
    (3, 2, 'H', 1, 'admin'),
    (3, 2, 'H', 2, 'admin'),
    (3, 2, 'H', 3, 'admin'),
    (3, 2, 'H', 4, 'admin'),
    (3, 2, 'H', 5, 'admin'),
    (3, 2, 'H', 6, 'admin'),
    (3, 1, 'KO', 7, 'admin'),
    (3, 2, 'H', 8, 'admin'),
    (3, 2, 'H', 9, 'admin'),
    (3, 2, 'H', 10, 'admin'),
    (3, 2, 'H', 11, 'admin'),
    (3, 2, 'H', 12, 'admin'),
    (3, 2, 'H', 13, 'admin'),

    -- Hàng I
    (3, 2, 'I', 1, 'admin'),
    (3, 2, 'I', 2, 'admin'),
    (3, 2, 'I', 3, 'admin'),
    (3, 2, 'I', 4, 'admin'),
    (3, 2, 'I', 5, 'admin'),
    (3, 2, 'I', 6, 'admin'),
    (3, 1, 'KO', 7, 'admin'),
    (3, 2, 'I', 8, 'admin'),
    (3, 2, 'I', 9, 'admin'),
    (3, 2, 'I', 10, 'admin'),
    (3, 2, 'I', 11, 'admin'),
    (3, 2, 'I', 12, 'admin'),
    (3, 2, 'I', 13, 'admin'),

    -- Hàng J
    (3, 2, 'J', 1, 'admin'),
    (3, 2, 'J', 2, 'admin'),
    (3, 2, 'J', 3, 'admin'),
    (3, 2, 'J', 4, 'admin'),
    (3, 2, 'J', 5, 'admin'),
    (3, 2, 'J', 6, 'admin'),
    (3, 1, 'KO', 7, 'admin'),
    (3, 2, 'J', 8, 'admin'),
    (3, 2, 'J', 9, 'admin'),
    (3, 2, 'J', 10, 'admin'),
    (3, 2, 'J', 11, 'admin'),
    (3, 2, 'J', 12, 'admin'),
    (3, 2, 'J', 13, 'admin'),

    -- Hàng K
    (3, 2, 'K', 1, 'admin'),
    (3, 2, 'K', 2, 'admin'),
    (3, 2, 'K', 3, 'admin'),
    (3, 2, 'K', 4, 'admin'),
    (3, 2, 'K', 5, 'admin'),
    (3, 2, 'K', 6, 'admin'),
    (3, 1, 'KO', 7, 'admin'),
    (3, 2, 'K', 8, 'admin'),
    (3, 2, 'K', 9, 'admin'),
    (3, 2, 'K', 10, 'admin'),
    (3, 2, 'K', 11, 'admin'),
    (3, 2, 'K', 12, 'admin'),
    (3, 2, 'K', 13, 'admin'),

    -- Hàng L
    (3, 2, 'L', 1, 'admin'),
    (3, 2, 'L', 2, 'admin'),
    (3, 2, 'L', 3, 'admin'),
    (3, 2, 'L', 4, 'admin'),
    (3, 2, 'L', 5, 'admin'),
    (3, 2, 'L', 6, 'admin'),
    (3, 1, 'KO', 7, 'admin'),
    (3, 2, 'L', 8, 'admin'),
    (3, 2, 'L', 9, 'admin'),
    (3, 2, 'L', 10, 'admin'),
    (3, 2, 'L', 11, 'admin'),
    (3, 2, 'L', 12, 'admin'),
    (3, 2, 'L', 13, 'admin'),

    -- Hàng M
    (3, 2, 'M', 1, 'admin'),
    (3, 2, 'M', 2, 'admin'),
    (3, 2, 'M', 3, 'admin'),
    (3, 2, 'M', 4, 'admin'),
    (3, 2, 'M', 5, 'admin'),
    (3, 2, 'M', 6, 'admin'),
    (3, 1, 'KO', 7, 'admin'),
    (3, 2, 'M', 8, 'admin'),
    (3, 2, 'M', 9, 'admin'),
    (3, 2, 'M', 10, 'admin'),
    (3, 2, 'M', 11, 'admin'),
    (3, 2, 'M', 12, 'admin'),
    (3, 2, 'M', 13, 'admin'),

    -- ==========================================
    -- KHU VỰC SWEETBOX (Màu xanh dương)
    -- ==========================================
    -- Hàng N
    (3, 3, 'N', 1, 'admin'),
    (3, 3, 'N', 2, 'admin'),
    (3, 3, 'N', 3, 'admin'),
    (3, 3, 'N', 4, 'admin'),
    (3, 3, 'N', 5, 'admin'),
    (3, 3, 'N', 6, 'admin'),
    (3, 1, 'KO', 7, 'admin'),
    (3, 3, 'N', 8, 'admin'),
    (3, 3, 'N', 9, 'admin'),
    (3, 3, 'N', 10, 'admin'),
    (3, 3, 'N', 11, 'admin'),
    (3, 3, 'N', 12, 'admin'),
    (3, 3, 'N', 13, 'admin'),

    -- Hàng O
    (3, 3, 'O', 1, 'admin'),
    (3, 3, 'O', 2, 'admin'),
    (3, 3, 'O', 3, 'admin'),
    (3, 3, 'O', 4, 'admin'),
    (3, 3, 'O', 5, 'admin'),
    (3, 3, 'O', 6, 'admin'),
    (3, 1, 'KO', 7, 'admin'),
    (3, 3, 'O', 8, 'admin'),
    (3, 3, 'O', 9, 'admin'),
    (3, 3, 'O', 10, 'admin'),
    (3, 3, 'O', 11, 'admin'),
    (3, 3, 'O', 12, 'admin'),
    (3, 3, 'O', 13, 'admin');

INSERT INTO seat (hall_id, seat_type_id, row_label, col_number, created_by)
VALUES
    -- ==========================================
    -- KHU VỰC GHẾ THƯỜNG (Màu xám nhạt)
    -- ==========================================
    -- Hàng A
    (2, 1, 'A', 1, 'admin'),
    (2, 1, 'A', 2, 'admin'),
    (2, 1, 'A', 3, 'admin'),
    (2, 1, 'A', 4, 'admin'),
    (2, 1, 'A', 5, 'admin'),
    (2, 1, 'A', 6, 'admin'),
    (2, 1, 'KO', 7, 'admin'),
    (2, 1, 'A', 8, 'admin'),
    (2, 1, 'A', 9, 'admin'),
    (2, 1, 'A', 10, 'admin'),
    (2, 1, 'A', 11, 'admin'),
    (2, 1, 'A', 12, 'admin'),
    (2, 1, 'A', 13, 'admin'),

    -- Hàng B
    (2, 1, 'B', 1, 'admin'),
    (2, 1, 'B', 2, 'admin'),
    (2, 1, 'B', 3, 'admin'),
    (2, 1, 'B', 4, 'admin'),
    (2, 1, 'B', 5, 'admin'),
    (2, 1, 'B', 6, 'admin'),
    (2, 1, 'KO', 7, 'admin'),
    (2, 1, 'B', 8, 'admin'),
    (2, 1, 'B', 9, 'admin'),
    (2, 1, 'B', 10, 'admin'),
    (2, 1, 'B', 11, 'admin'),
    (2, 1, 'B', 12, 'admin'),
    (2, 1, 'B', 13, 'admin'),

    -- Hàng C
    (2, 1, 'C', 1, 'admin'),
    (2, 1, 'C', 2, 'admin'),
    (2, 1, 'C', 3, 'admin'),
    (2, 1, 'C', 4, 'admin'),
    (2, 1, 'C', 5, 'admin'),
    (2, 1, 'C', 6, 'admin'),
    (2, 1, 'KO', 7, 'admin'),
    (2, 1, 'C', 8, 'admin'),
    (2, 1, 'C', 9, 'admin'),
    (2, 1, 'C', 10, 'admin'),
    (2, 1, 'C', 11, 'admin'),
    (2, 1, 'C', 12, 'admin'),
    (2, 1, 'C', 13, 'admin'),

    -- Hàng D
    (2, 1, 'D', 1, 'admin'),
    (2, 1, 'D', 2, 'admin'),
    (2, 1, 'D', 3, 'admin'),
    (2, 1, 'D', 4, 'admin'),
    (2, 1, 'D', 5, 'admin'),
    (2, 1, 'D', 6, 'admin'),
    (2, 1, 'KO', 7, 'admin'),
    (2, 1, 'D', 8, 'admin'),
    (2, 1, 'D', 9, 'admin'),
    (2, 1, 'D', 10, 'admin'),
    (2, 1, 'D', 11, 'admin'),
    (2, 1, 'D', 12, 'admin'),
    (2, 1, 'D', 13, 'admin'),

    -- Hàng E
    (2, 1, 'E', 1, 'admin'),
    (2, 1, 'E', 2, 'admin'),
    (2, 1, 'E', 3, 'admin'),
    (2, 1, 'E', 4, 'admin'),
    (2, 1, 'E', 5, 'admin'),
    (2, 1, 'E', 6, 'admin'),
    (2, 1, 'KO', 7, 'admin'),
    (2, 1, 'E', 8, 'admin'),
    (2, 1, 'E', 9, 'admin'),
    (2, 1, 'E', 10, 'admin'),
    (2, 1, 'E', 11, 'admin'),
    (2, 1, 'E', 12, 'admin'),
    (2, 1, 'E', 13, 'admin'),

    -- Hàng F
    (2, 1, 'F', 1, 'admin'),
    (2, 1, 'F', 2, 'admin'),
    (2, 1, 'F', 3, 'admin'),
    (2, 1, 'F', 4, 'admin'),
    (2, 1, 'F', 5, 'admin'),
    (2, 1, 'F', 6, 'admin'),
    (2, 1, 'KO', 7, 'admin'),
    (2, 1, 'F', 8, 'admin'),
    (2, 1, 'F', 9, 'admin'),
    (2, 1, 'F', 10, 'admin'),
    (2, 1, 'F', 11, 'admin'),
    (2, 1, 'F', 12, 'admin'),
    (2, 1, 'F', 13, 'admin'),

    -- Hàng G
    (2, 1, 'G', 1, 'admin'),
    (2, 1, 'G', 2, 'admin'),
    (2, 1, 'G', 3, 'admin'),
    (2, 1, 'G', 4, 'admin'),
    (2, 1, 'G', 5, 'admin'),
    (2, 1, 'G', 6, 'admin'),
    (2, 1, 'KO', 7, 'admin'),
    (2, 1, 'G', 8, 'admin'),
    (2, 1, 'G', 9, 'admin'),
    (2, 1, 'G', 10, 'admin'),
    (2, 1, 'G', 11, 'admin'),
    (2, 1, 'G', 12, 'admin'),
    (2, 1, 'G', 13, 'admin'),

    -- ==========================================
    -- KHU VỰC GHẾ VIP (Màu hồng nhạt)
    -- ==========================================
    -- Hàng H
    (2, 2, 'H', 1, 'admin'),
    (2, 2, 'H', 2, 'admin'),
    (2, 2, 'H', 3, 'admin'),
    (2, 2, 'H', 4, 'admin'),
    (2, 2, 'H', 5, 'admin'),
    (2, 2, 'H', 6, 'admin'),
    (2, 1, 'KO', 7, 'admin'),
    (2, 2, 'H', 8, 'admin'),
    (2, 2, 'H', 9, 'admin'),
    (2, 2, 'H', 10, 'admin'),
    (2, 2, 'H', 11, 'admin'),
    (2, 2, 'H', 12, 'admin'),
    (2, 2, 'H', 13, 'admin'),

    -- Hàng I
    (2, 2, 'I', 1, 'admin'),
    (2, 2, 'I', 2, 'admin'),
    (2, 2, 'I', 3, 'admin'),
    (2, 2, 'I', 4, 'admin'),
    (2, 2, 'I', 5, 'admin'),
    (2, 2, 'I', 6, 'admin'),
    (2, 1, 'KO', 7, 'admin'),
    (2, 2, 'I', 8, 'admin'),
    (2, 2, 'I', 9, 'admin'),
    (2, 2, 'I', 10, 'admin'),
    (2, 2, 'I', 11, 'admin'),
    (2, 2, 'I', 12, 'admin'),
    (2, 2, 'I', 13, 'admin'),

    -- Hàng J
    (2, 2, 'J', 1, 'admin'),
    (2, 2, 'J', 2, 'admin'),
    (2, 2, 'J', 3, 'admin'),
    (2, 2, 'J', 4, 'admin'),
    (2, 2, 'J', 5, 'admin'),
    (2, 2, 'J', 6, 'admin'),
    (2, 1, 'KO', 7, 'admin'),
    (2, 2, 'J', 8, 'admin'),
    (2, 2, 'J', 9, 'admin'),
    (2, 2, 'J', 10, 'admin'),
    (2, 2, 'J', 11, 'admin'),
    (2, 2, 'J', 12, 'admin'),
    (2, 2, 'J', 13, 'admin'),

    -- Hàng K
    (2, 2, 'K', 1, 'admin'),
    (2, 2, 'K', 2, 'admin'),
    (2, 2, 'K', 3, 'admin'),
    (2, 2, 'K', 4, 'admin'),
    (2, 2, 'K', 5, 'admin'),
    (2, 2, 'K', 6, 'admin'),
    (2, 1, 'KO', 7, 'admin'),
    (2, 2, 'K', 8, 'admin'),
    (2, 2, 'K', 9, 'admin'),
    (2, 2, 'K', 10, 'admin'),
    (2, 2, 'K', 11, 'admin'),
    (2, 2, 'K', 12, 'admin'),
    (2, 2, 'K', 13, 'admin'),

    -- Hàng L
    (2, 2, 'L', 1, 'admin'),
    (2, 2, 'L', 2, 'admin'),
    (2, 2, 'L', 3, 'admin'),
    (2, 2, 'L', 4, 'admin'),
    (2, 2, 'L', 5, 'admin'),
    (2, 2, 'L', 6, 'admin'),
    (2, 1, 'KO', 7, 'admin'),
    (2, 2, 'L', 8, 'admin'),
    (2, 2, 'L', 9, 'admin'),
    (2, 2, 'L', 10, 'admin'),
    (2, 2, 'L', 11, 'admin'),
    (2, 2, 'L', 12, 'admin'),
    (2, 2, 'L', 13, 'admin'),

    -- Hàng M
    (2, 2, 'M', 1, 'admin'),
    (2, 2, 'M', 2, 'admin'),
    (2, 2, 'M', 3, 'admin'),
    (2, 2, 'M', 4, 'admin'),
    (2, 2, 'M', 5, 'admin'),
    (2, 2, 'M', 6, 'admin'),
    (2, 1, 'KO', 7, 'admin'),
    (2, 2, 'M', 8, 'admin'),
    (2, 2, 'M', 9, 'admin'),
    (2, 2, 'M', 10, 'admin'),
    (2, 2, 'M', 11, 'admin'),
    (2, 2, 'M', 12, 'admin'),
    (2, 2, 'M', 13, 'admin'),

    -- ==========================================
    -- KHU VỰC SWEETBOX (Màu xanh dương)
    -- ==========================================
    -- Hàng N
    (2, 3, 'N', 1, 'admin'),
    (2, 3, 'N', 2, 'admin'),
    (2, 3, 'N', 3, 'admin'),
    (2, 3, 'N', 4, 'admin'),
    (2, 3, 'N', 5, 'admin'),
    (2, 3, 'N', 6, 'admin'),
    (2, 1, 'KO', 7, 'admin'),
    (2, 3, 'N', 8, 'admin'),
    (2, 3, 'N', 9, 'admin'),
    (2, 3, 'N', 10, 'admin'),
    (2, 3, 'N', 11, 'admin'),
    (2, 3, 'N', 12, 'admin'),
    (2, 3, 'N', 13, 'admin'),

    -- Hàng O
    (2, 3, 'O', 1, 'admin'),
    (2, 3, 'O', 2, 'admin'),
    (2, 3, 'O', 3, 'admin'),
    (2, 3, 'O', 4, 'admin'),
    (2, 3, 'O', 5, 'admin'),
    (2, 3, 'O', 6, 'admin'),
    (2, 1, 'KO', 7, 'admin'),
    (2, 3, 'O', 8, 'admin'),
    (2, 3, 'O', 9, 'admin'),
    (2, 3, 'O', 10, 'admin'),
    (2, 3, 'O', 11, 'admin'),
    (2, 3, 'O', 12, 'admin'),
    (2, 3, 'O', 13, 'admin');

####INSERT SHOWTIME#####
insert into showtime(movie_id, hall_id, date, start_time, type, created_by)
    value
    ###HALL 1 & MOVIE 2-6
    (2, 1, '2026-07-23', '08:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (3, 1, '2026-07-23', '10:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (4, 1, '2026-07-23', '12:00:00', 'Lòng tiếng', 'admin'),
    (5, 1, '2026-07-23', '14:00:00', 'Phụ đề tiếng Anh', 'admin'),
    (6, 1, '2026-07-23', '16:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (2, 1, '2026-07-23', '18:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (3, 1, '2026-07-23', '20:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (4, 1, '2026-07-23', '22:00:00', 'Lòng tiếng', 'admin'),
    (2, 1, '2026-07-24', '08:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (3, 1, '2026-07-24', '10:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (4, 1, '2026-07-24', '12:00:00', 'Lòng tiếng', 'admin'),
    (5, 1, '2026-07-24', '14:00:00', 'Phụ đề tiếng Anh', 'admin'),
    (6, 1, '2026-07-24', '16:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (2, 1, '2026-07-24', '18:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (3, 1, '2026-07-24', '20:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (4, 1, '2026-07-24', '22:00:00', 'Lòng tiếng', 'admin'),
    (2, 1, '2026-07-25', '08:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (3, 1, '2026-07-25', '10:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (4, 1, '2026-07-25', '12:00:00', 'Lòng tiếng', 'admin'),
    (5, 1, '2026-07-25', '14:00:00', 'Phụ đề tiếng Anh', 'admin'),
    (6, 1, '2026-07-25', '16:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (2, 1, '2026-07-25', '18:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (3, 1, '2026-07-25', '20:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (4, 1, '2026-07-25', '22:00:00', 'Lòng tiếng', 'admin'),
    (5, 1, '2026-07-25', '14:00:00', 'Phụ đề tiếng Anh', 'admin'),
    (6, 1, '2026-07-25', '16:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (2, 1, '2026-07-26', '08:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (3, 1, '2026-07-26', '10:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (4, 1, '2026-07-26', '12:00:00', 'Lòng tiếng', 'admin'),
    (5, 1, '2026-07-26', '14:00:00', 'Phụ đề tiếng Anh', 'admin'),
    (6, 1, '2026-07-26', '16:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (2, 1, '2026-07-26', '18:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (3, 1, '2026-07-26', '20:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (4, 1, '2026-07-26', '22:00:00', 'Lòng tiếng', 'admin'),
    (5, 1, '2026-07-26', '14:00:00', 'Phụ đề tiếng Anh', 'admin'),
    (6, 1, '2026-07-26', '16:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (2, 1, '2026-07-27', '08:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (3, 1, '2026-07-27', '10:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (4, 1, '2026-07-27', '12:00:00', 'Lòng tiếng', 'admin'),
    (5, 1, '2026-07-27', '14:00:00', 'Phụ đề tiếng Anh', 'admin'),
    (6, 1, '2026-07-27', '16:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (2, 1, '2026-07-27', '18:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (3, 1, '2026-07-27', '20:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (4, 1, '2026-07-27', '22:00:00', 'Lòng tiếng', 'admin'),
    (5, 1, '2026-07-27', '14:00:00', 'Phụ đề tiếng Anh', 'admin'),
    (6, 1, '2026-07-27', '16:00:00', 'Phụ đề tiếng Việt', 'admin');

####HAL 2 & MOVIE 3-8
insert into showtime(movie_id, hall_id, date, start_time, type, created_by)
    value
    (3, 2, '2026-07-24', '08:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (4, 2, '2026-07-24', '10:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (5, 2, '2026-07-24', '12:00:00', 'Lòng tiếng', 'admin'),
    (6, 2, '2026-07-24', '14:00:00', 'Phụ đề tiếng Anh', 'admin'),
    (7, 2, '2026-07-24', '16:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (8, 2, '2026-07-24', '18:00:00', 'Lòng tiếng', 'admin'),
    (3, 2, '2026-07-24', '20:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (4, 2, '2026-07-24', '22:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (3, 2, '2026-07-25', '08:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (4, 2, '2026-07-25', '10:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (5, 2, '2026-07-25', '12:00:00', 'Lòng tiếng', 'admin'),
    (6, 2, '2026-07-25', '14:00:00', 'Phụ đề tiếng Anh', 'admin'),
    (7, 2, '2026-07-25', '16:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (8, 2, '2026-07-25', '18:00:00', 'Lòng tiếng', 'admin'),
    (3, 2, '2026-07-25', '20:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (4, 2, '2026-07-25', '22:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (3, 2, '2026-07-26', '08:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (4, 2, '2026-07-26', '10:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (5, 2, '2026-07-26', '12:00:00', 'Lòng tiếng', 'admin'),
    (6, 2, '2026-07-26', '14:00:00', 'Phụ đề tiếng Anh', 'admin'),
    (7, 2, '2026-07-26', '16:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (8, 2, '2026-07-26', '18:00:00', 'Lòng tiếng', 'admin'),
    (3, 2, '2026-07-26', '20:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (4, 2, '2026-07-26', '22:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (3, 2, '2026-07-27', '08:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (4, 2, '2026-07-27', '10:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (5, 2, '2026-07-27', '12:00:00', 'Lòng tiếng', 'admin'),
    (6, 2, '2026-07-27', '14:00:00', 'Phụ đề tiếng Anh', 'admin'),
    (7, 2, '2026-07-27', '16:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (8, 2, '2026-07-27', '18:00:00', 'Lòng tiếng', 'admin'),
    (3, 2, '2026-07-27', '20:00:00', 'Phụ đề tiếng Việt', 'admin'),
    (4, 2, '2026-07-27', '22:00:00', 'Phụ đề tiếng Việt', 'admin');


######INSERT SPECIAL LIST#####
insert into special_list(name, code, description, list, created_by)
    value ('Đang chiếu', 'SHOWING', 'Danh sách các bộ phim đang được chiếu tại rạp', JSON_ARRAY(1, 2, 5, 6), 'admin'),
    ('Sắp chiếu', 'COMING_SOON', 'Danh sách các bộ phim sắp được chiếu tại rạp', JSON_ARRAY(3, 4), 'admin'),
    ('Top Trending', 'TOP_TRENDING', 'Danh sách các bộ phim đang được quan tâm nhiều nhất', JSON_ARRAY(1, 3, 5),
     'admin'),
    ('Top bán chạy', 'TOP_SELLING', 'Danh sách các bộ phim có doanh thu cao nhất', JSON_ARRAY(2, 4, 6), 'admin');

####INSERT ADIENCE TYPE####
insert into audience_type(name, description, created_by)
    value ('Người lớn', 'Khách hàng từ 18 tuổi trở lên', 'admin'),
    ('U22', 'Khách hàng từ 6 đến 22 tuổi', 'admin'),
    ('Trẻ nhỏ', 'Khách hàng từ 3 đến 5 tuổi', 'admin'),
    ('Sinh viên', 'Khách hàng là sinh viên có thẻ sinh viên hợp lệ', 'admin'),
    ('Người cao tuổi', 'Khách hàng từ 60 tuổi trở lên', 'admin'),
    ('Người khuyết tật', 'Khách hàng có giấy tờ chứng minh là người khuyết tật', 'admin');

####INSERT PRICE LIST####
##2D Hall
insert into price_list(hall_type_id, seat_type_id, audience_type_id, name, price, days, created_by)
    value
###Adult
    (1, 1, 1, '2d-Normal-Adult-Weekday', 70000, JSON_ARRAY('MON', 'TUE', 'WED', 'THU'), 'admin'),
    (1, 1, 1, '2d-Normal-Adult-Weekend', 95000, JSON_ARRAY('FRI', 'SAT', 'SUN'), 'admin'),
    (1, 1, 1, '2d-Normal-Adult-Holiday', 100000, JSON_ARRAY('HOLIDAY'), 'admin'),
    (1, 2, 1, '2d-VIP-Adult-Weekday', 800000, JSON_ARRAY('MON', 'TUE', 'WED', 'THU'), 'admin'),
    (1, 2, 1, '2d-VIP-Adult-Weekend', 105000, JSON_ARRAY('FRI', 'SAT', 'SUN'), 'admin'),
    (1, 2, 1, '2d-VIP-Adult-Holiday', 110000, JSON_ARRAY('HOLIDAY'), 'admin'),
    (1, 3, 1, '2d-Sweetbox-Adult-Weekday', 180000, JSON_ARRAY('MON', 'TUE', 'WED', 'THU'), 'admin'),
    (1, 3, 1, '2d-Sweetbox-Adult-Weekend', 240000, JSON_ARRAY('FRI', 'SAT', 'SUN'), 'admin'),
    (1, 3, 1, '2d-Normal-Adult-Holiday', 260000, JSON_ARRAY('HOLIDAY'), 'admin') ,
###U22
    (1, 1, 2, '2d-Normal-U22-Weekday', 55000, JSON_ARRAY('MON', 'TUE', 'WED', 'THU', 'FRI'), 'admin'),
    (1, 1, 2, '2d-Normal-U22-Weekend', 60000, JSON_ARRAY('SAT', 'SUN'), 'admin'),
    (1, 1, 2, '2d-Normal-U22-Holiday', 65000, JSON_ARRAY('HOLIDAY'), 'admin'),
    (1, 2, 2, '2d-VIP-U22-Weekday', 55000, JSON_ARRAY('MON', 'TUE', 'WED', 'THU', 'FRI'), 'admin'),
    (1, 2, 2, '2d-VIP-U22-Weekend', 70000, JSON_ARRAY('SAT', 'SUN'), 'admin'),
    (1, 2, 2, '2d-VIP-U22-Holiday', 75000, JSON_ARRAY('HOLIDAY'), 'admin'),
    (1, 3, 2, '2d-Sweetbox-U22-Weekday', 140000, JSON_ARRAY('MON', 'TUE', 'WED', 'THU', 'FRI'), 'admin'),
    (1, 3, 2, '2d-Sweetbox-U22-Weekend', 170000, JSON_ARRAY('SAT', 'SUN'), 'admin'),
    (1, 3, 2, '2d-Sweetbox-U22-Holiday', 180000, JSON_ARRAY('HOLIDAY'), 'admin'),
###Student
    (1, 1, 3, '2d-Normal-Student-Weekday', 55000, JSON_ARRAY('MON', 'TUE', 'WED', 'THU', 'FRI'), 'admin'),
    (1, 1, 3, '2d-Normal-Student-Weekend', 60000, JSON_ARRAY('SAT', 'SUN'), 'admin'),
    (1, 1, 3, '2d-Normal-Student-Holiday', 65000, JSON_ARRAY('HOLIDAY'), 'admin'),
    (1, 2, 3, '2d-VIP-Student-Weekday', 55000, JSON_ARRAY('MON', 'TUE', 'WED', 'THU', 'FRI'), 'admin'),
    (1, 2, 3, '2d-VIP-Student-Weekend', 70000, JSON_ARRAY('SAT', 'SUN'), 'admin'),
    (1, 2, 3, '2d-VIP-Student-Holiday', 75000, JSON_ARRAY('HOLIDAY'), 'admin'),
    (1, 3, 3, '2d-Sweetbox-Student-Weekday', 140000, JSON_ARRAY('MON', 'TUE', 'WED', 'THU', 'FRI'), 'admin'),
    (1, 3, 3, '2d-Sweetbox-Student-Weekend', 170000, JSON_ARRAY('SAT', 'SUN'), 'admin'),
    (1, 3, 3, '2d-Sweetbox-Student-Holiday', 180000, JSON_ARRAY('HOLIDAY'), 'admin');
