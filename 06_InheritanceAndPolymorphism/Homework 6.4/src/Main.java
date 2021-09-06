
    public static void main(String args[]) {
        S s = new S();
        System.out.println(s.x);
    class S {
        public int x = 0;

        public S() {
            x++;
        }
    }

