public interface IMst<T> {
     void selectVertex(T vertex);
     Graph<T> makeMst();
     int mstEdgesAmount();
     int mstEdgesWeightSum();
}
