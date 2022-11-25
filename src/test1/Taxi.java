package test1;

public class Taxi extends Transportation {

    String destination;			// 목적지
    int distance;				// 목적지까지 거리
    int maxPass = 4;			// 최대 승객수
    int defaultDistance = 1;	// 기본 거리
    int defaultCost = 3000;		// 기본 요금
    int perDistance = 1000;		// 거리당 요금
    static String status = "일반";		// 상태
    int speed = 0;				// 속도
    int total = 0;				// 누적 금액
    int cost;					// 승객이 지불할 금액

    // 택시 번호 지정 [고유값으로 생성되어야 되기에 랜덤함수로 함]
    public Taxi() {
        this.num = (int)(Math.random()*100+1);
        // 랜덤함수는 기본형이 Double 형이기에 (int)로 정수화
        // 1부터 값을 뽑고 싶다면 +1 => 랜덤 함수는 0부터 나오기 때문에
        System.out.println("택시 번호 : "+num);
        Taxi.drive();
    }

    static boolean drive() {
        if (!gasLeft()) {
            status = "운행 불가";
            System.out.println("주유 필요");
            return false;
        }
        return true;
    }

    static boolean gasLeft() {
        return false;
    }

    // 탑승		승객			목적지		거리
    @Override
    void board(int pass, String dest, int dis) {
        if(status == "일반") {

            if(pass > 4)
                System.out.println("최대 승객 수 초과");
            else {

                if(dis==1)
                    cost = defaultCost+ (perDistance*dis);
                else
                    cost = defaultCost+ (perDistance*(dis-1));
                status = "운행중";
                System.out.println("탑승 승객 수 = "+pass);
                System.out.println("잔여 승객 수 = "+ (maxPass-pass));
                System.out.println("기본 요금 확인 = "+defaultCost);
                System.out.println("목적지 = "+dest);
                System.out.println("목적지까지 거리 = "+ dis+"km");
                System.out.println("지불할 요금 = "+cost);
                total += cost;

            }
        }
    }

    @Override
    void refuel() {
    }
    // 주유량
    @Override
    int refuel(int gas) {
        currentGas += gas;
        if(!gasLeft()) {
            status = "운행 불가";
        }
        return currentGas;
    }

    @Override
    int board(int pass) {
        return 0;
    }

    // 요금 지불
    int pay() {
        status = "일반";
        maxPass = 4;
        System.out.println("누적 요금 = "+ total);
        if(!gasLeft())
            System.out.println("주유 필요");
        cost = 0;
        return total;
    }

    void passenger(int pass) {
        if(pass > 4)
            System.out.println("최대 승객 수 초과");
    }
    // 속도변경
    int changeSpeed(int acceleration) {
        //주유 상태를 체크하고 주유량이 10 이상이어야 운행할 수 있음
        if(gasLeft()) {
            this.acceleration = acceleration;

            speed += acceleration;

            System.out.println("현재 속도는 "+ speed+"입니다.");

        }
        System.out.println("주유량을 확인해주세요."+currentGas);
        return currentGas;
    }
}