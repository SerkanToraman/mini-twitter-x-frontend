🚀 **Hoş Geldiniz, Geleceğin Frontend Geliştiricileri!** 🚀

Bu projede, temel bir Twitter benzeri uygulama oluşturarak React, API entegrasyonu ve modern UI/UX prensipleri hakkında deneyim kazanacaksınız.

🔥 **Gelin Başlayalım!** 🔥

## Başlangıç Adımları:

1. En son bölümde verilen Figma tasarımını inceleyin.
2. Yeni bir React projesi başlatın (vite veya cra kullanabilirsiniz).
3. Gerekli kütüphaneleri (Axios,   React Router vb.) yükleyin.
4. Projede global state yönetimi için `useContext` veya `Redux` kullanmayı düşünebilirsiniz. Özellikle kullanıcı oturumu veya tweetlerin genel durumu için ideal olabilir.

### Projeyi Adım Adım Oluşturma:

**1. Kimlik Doğrulama:**

[//]: # ()
- Kayıt Ol, Giriş Yap ve Çıkış Yap işlevselliği oluşturun.
- İpucu: Kullanıcı oturumu ve kimlik bilgilerini global bir state'de saklamak için `useContext` veya `Redux` kullanmayı düşünebilirsiniz. ✨

**2. Ana Tweet Akışı:**

- API'den tweetleri getirip, ana sayfada listeleme yapın.
- Yeni bir tweet gönderme özelliği ekleyin.

**3. Tweet İşlemleri:**

- Tweet düzenleme, silme, retweetleme, beğenme/beğenmeme ve yorum yapma özelliklerini ekleyin.

## Geliştirme Süreci

Tasarımınızı oluşturmak için şu Figma linkini kullanabilirsiniz: [Twitter Desktop Tasarımı](<https://www.figma.com/community/file/1013470200889674140/Twitter-desktop-pages-(feed%2C-sigup%2C-login%2C-profile)>). Bu tasarım, masaüstü sürümü için oturum açma, kayıt olma ve ana feed sayfalarını içeriyor. Tasarım ile çalışma hakkında daha fazla ipucu için bu dökümanın son kısmına bakabilirsiniz.

Hadi projemizi biraz daha detaylandıralım ve her adımın ne anlama geldiğine daha derinlemesine dalalım. Elinizde güzel bir Figma tasarımınız olduğuna göre, bunu kodla canlandırmaya ne dersiniz? 😊

### Bölüm 1: Yapılandırma & Temel Kurulum

- Yeni bir React uygulaması başlatın ve temel bağımlılıkları (axios, react-router-dom) kurun.
- Global state yönetimi için, `useContext` veya `Redux`'ı düşünün. Oturum bilgisi, tweetler, yorumlar gibi genel verileri saklamak için harika!

### Bölüm 2: Rotalama (Routing) ve Ana Bileşenler

React Router'ı kullanarak, uygulamanızın temel navigasyonunu oluşturun.

**Ana Rotalar:**

1. **Ana Sayfa (/)**: Burada tweet akışınız yer alacak.
2. **Kayıt Ol (/signup)**: Yeni kullanıcıların kayıt olabileceği bir form.
3. **Giriş Yap (/signin)**: Kullanıcıların oturum açabileceği bir form.

💡 **İpucu:** `useHistory` hook'unu kullanarak, başarılı bir giriş yaptıktan sonra kullanıcıyı ana sayfaya yönlendirebilirsiniz!

### Bölüm 3: Bileşenler & İşlevsellik

React'ın güzelliklerinden biri de bileşen tabanlı yapısıdır. İşte bazı temel bileşenler:

**1. Navbar Bileşeni:**

- Uygulamanın üst kısmında yer alacak, ana sayfaya geri dönme ve oturum işlemleri için bağlantılar içerecek.

**2. TweetList Bileşeni:**

- Ana sayfada yer alacak ve API'den alınan tweetleri listelemek için kullanılacak.

**3. TweetCard Bileşeni:**

- Her bir tweeti göstermek için kullanılacak. Retweet, beğenme ve yorum yapma işlevlerini içerecek.

💡 **İpucu:** TweetCard içerisindeki beğeni ve retweet işlevlerini yönetmek için, local state kullanabilirsiniz!

**4. ReplyModal Bileşeni:**

- Bir tweet'e yorum yapmak için kullanılacak popup/modal bileşeni. (Tasarımda yok)

💡 **İpucu:** Bu modalı açmak/kapatmak için state ve useEffect kullanabilirsiniz!

### Bölüm 4: API İstekleri & State Yönetimi

- Axios ile API istekleri yapın.
- Kullanıcının oturum bilgilerini, tweetleri ve yorumları global state'te yönetmeyi düşünün.

💡 **İpucu:** API cevaplarını yakalamak ve hataları yönetmek için promise then catch blokları kullanın.

#### Endpoints & Requests:

**Profil Kimlik Doğrulama:**

| Metod | URL                       | Tanım     |
| ----- | ------------------------- | --------- |
| POST  | `${URL}/profile/register` | Kayıt Ol  |
| POST  | `${URL}/profile/logout`   | Çıkış Yap |
| POST  | `${URL}/profile/login`    | Giriş Yap |

**Tweet İşlemleri:**

| Metod  | URL                       | Tanım                 |
| ------ | ------------------------- | --------------------- |
| GET    | `${URL}/tweet/`           | Bütün Tweetleri Getir |
| GET    | `${URL}/tweet/id`         | Bir Tweet Getir       |
| POST   | `${URL}/tweet`            | Yeni Tweet            |
| PUT    | `${URL}/tweet/id`         | Tweet Düzenle         |
| DELETE | `${URL}/tweet/id`         | Tweet Sil             |
| POST   | `${URL}/tweet/like/id`    | Beğen                 |
| DELETE | `${URL}/tweet/like/id`    | Beğenme               |
| POST   | `${URL}/tweet/retweet/id` | Retweet               |
| POST   | `${URL}/tweet/reply/id`   | Yorum Yap             |
| DELETE | `${URL}/tweet/reply/id`   | Yorum Sil             |

API için projede ilerledikçe [Mini Twitter X - Mock API Server](https://github.com/Workintech/mini-twitter-x-mock-api-server) adresindeki repoyu kullanabilirsiniz.

## Tasarım ile Çalışma

🖌 **Tasarım ve Parçalarını Dışarı Aktarın**
🛠 **Figma Asset'lerini Dışa Aktarma**
Tasarımınızdaki öğeleri dışa aktarmak için:

1. Figma'da istediğiniz öğenin üzerine gelin.
2. Sağ tarafta "Export" bölümünü göreceksiniz.
3. Format ve boyutu seçerek "Export" butonuna tıklayın.
4. İndirilen öğeleri projenizde uygun klasörde saklayın.

Tabii ki, Figma'nın kullanımındaki bazı temel adımlara odaklanalım! Figma, tasarımınızda kullanılan renkleri ve fontları rahatça okumanızı sağlar. Bu sayede uygulamanızda kullanılacak stil bilgilerini kolayca alabilirsiniz. 🎨

### Figma'da Renkleri Okuma:

1. **Renk Seçimi:** Figma dosyasında istediğiniz bir öğeye tıklayın (örneğin, bir buton veya metin). Bu, öğenin özelliklerini sağ tarafta gösterecektir.
2. **Renk Kodunu Kopyalama:** Sağ tarafta, 'Fill' bölümünde, renk kodunu (genellikle HEX olarak) göreceksiniz. Bu kodu doğrudan kopyalayarak CSS veya React bileşenlerinizde kullanabilirsiniz.

💡 **İpucu:** Figma, RGB, HEX ve HSL dahil olmak üzere farklı renk formatlarını destekler. İhtiyacınıza uygun formatı seçmek için renk kodunun yanındaki küçük renk kutusuna tıklayın.

### Figma'da Fontları Okuma:

1. **Metin Seçimi:** Tasarımınızdaki herhangi bir metin öğesine tıklayın.
2. **Font Bilgileri:** Sağ taraftaki 'Text' bölümünde, font ailesi (örneğin, "Roboto"), font ağırlığı (örneğin, "Regular" veya "Bold") ve font boyutu gibi metin özelliklerini göreceksiniz.
3. Eğer projenizde bu fontları kullanmayı planlıyorsanız, örneğin Google Fonts'tan ilgili fontları projenize dahil edebilirsiniz.

💡 **İpucu:** Font ağırlıklarını CSS'de tanımlarken, "Regular" için `font-weight: 400;` ve "Bold" için `font-weight: 700;` gibi değerleri kullanabilirsiniz.

---

💡 **Son Not:** Projeyi oluştururken kodunuzu sık sık gözden geçirin, tasarımı takip edin ve en önemlisi eğlenin! 🎉 Eğer bir yerde takılırsanız, hatırlayın: Her hata, öğrenme fırsatıdır. Bazen bir şeylerin neden çalışmadığını anlamak, nasıl çalıştığını anlamaktan bile daha öğreticidir. Başarılar dileriz! 🌟🚀
