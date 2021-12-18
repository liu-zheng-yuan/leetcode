          int[] rowDelta = new int[]{1, -1, 0, 0};
          int[] colDelta = new int[]{0, 0 ,-1, 1};


          public boolean exist(char[][] board, String word) {
              if (board.length == 0) {
                  return false;
              } 
              for (int i = 0; i < board.length; i++) {
                  for (int j = 0; j < board[0].length; j++) {
                      if (search(board, word, 0, i, j)) {
                          return true;
                      }
                  }
              }
              return false;
          }

          public boolean search(char[][] board, String word, int index, int row, int col) {
              //客观限制条件不满足
              if (!isValid(board, word, index, row, col)) {
                  return false;
              }
              //业务限制条件不满足
              if (board[row][col] != word.charAt(index)) {
                  return false;
              }
              //搜索终点
              if (index == word.length() - 1) {
                  return true;
              }

              boolean result = false;
              //标记访问
              board[row][col] = '\0';
              for (int i = 0; i < 4; i++) {
                  int nowRow = row + rowDelta[i];
                  int nowCol = col + colDelta[i];
                  result = result | search(board, word, index + 1, nowRow, nowCol);
              }
              board[row][col] = word.charAt(index);
              return result;
          }

          private boolean isValid(char[][] board, String word, int index, int row, int col) {
              return index<word.length() && row < board.length && col < board[0].length && row >= 0 && col >= 0 && index >= 0;
          }