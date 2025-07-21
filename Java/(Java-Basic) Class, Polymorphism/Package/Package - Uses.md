-----
### 패키지 활용
-----
1. 실제 패키지가 어떤 식으로 사용되는지 예제를 통해서 확인
2. 실제 동작하는 코드는 아니지만, 큰 애플리케이션은 대략 이런식으로 패키지를 구성한다고 이해하면 됨
3. 프로젝트 규모와 아키텍처에 따라서 달라질 수 있음
4. 전체 구조도
```
com.helloshop
    user
        User
        UserService
    product
        Product
        ProductService
    order
        Order
        OrderService
        OrderHistory
```

5. com.helloshop.user 패키지
```java
package com.helloshop.user;

public class User {
    String userId;
    String name;
}
```
```java
package com.helloshop.user;

public class UserService {
}
```

6. com.helloshop.product 패키지
```java
package com.helloshop.product;

public class Product {
    String productId;
    int price;
}
```
```java
package com.helloshop.product;

public class ProductService {
}
```

7. com.helloshop.order 패키지
```java
package com.helloshop.order;

import com.helloshop.product.Product;
import com.helloshop.user.User;

public class Order {
    User user;
    Product product;
    
    public Order(User user, Product product) {
        this.user = user;
        this.product = product;
    }
}
```
   - 다른 패키지의 기능이 필요하면 import 를 사용하면 됨
   - 생성자를 보면 public : public이어야 다른 패키지에서 생성자를 호출할 수 있음

```java
package com.helloshop.order;

import com.helloshop.product.Product;
import com.helloshop.user.User;

public class OrderService {
    public void order() {
        User user = new User();
        Product product = new Product();
        Order order = new Order(user, product);
    }
}
```
```java
package com.helloshop.order;

public class OrderHistory {
}
```

8. 패키지를 구성할 때 서로 관련된 클래스는 하나의 패키지에 모으고, 관련이 적은 클래스는 다른 패키지로 분리하는 것이 좋음

