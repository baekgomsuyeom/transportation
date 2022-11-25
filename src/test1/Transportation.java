package test1;

public abstract class Transportation {
    int num;
    int acceleration;
    int currentGas;
    String status;

    // 탑승		승객			목적지		거리
    abstract void board(int pass, String dest, int dis);

    // 주유량
    abstract void refuel();

    abstract int refuel(int gas) ;

    abstract int board(int pass) ;

}